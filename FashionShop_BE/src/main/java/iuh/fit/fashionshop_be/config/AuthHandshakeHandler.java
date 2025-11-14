/*
 * @ (#) AuthHandshakeHandler.java     1.0    13-Nov-25
 */
package iuh.fit.fashionshop_be.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import iuh.fit.fashionshop_be.config.CustomUserDetailsService;
import iuh.fit.fashionshop_be.config.security.CustomerDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Map;

/**
 * Handshake handler that extracts a JWT from the HTTP handshake (query param `token` or `Authorization` header)
 * and sets a Principal for the WebSocket session so that SimpMessagingTemplate.convertAndSendToUser(...) works.
 */
public class AuthHandshakeHandler extends DefaultHandshakeHandler {

    private final CustomUserDetailsService userDetailsService;
    private final String secretKey;

    public AuthHandshakeHandler(CustomUserDetailsService userDetailsService, String secretKey) {
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
    }

    @Override
    protected Principal determineUser(org.springframework.http.server.ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        try {
            // try header first
            String token = null;
            if (request.getHeaders().containsKey("Authorization")) {
                String auth = request.getHeaders().getFirst("Authorization");
                if (auth != null && auth.startsWith("Bearer ")) {
                    token = auth.substring(7);
                }
            }

            // fallback to query param ?token=...
            if (token == null) {
                String query = request.getURI().getQuery();
                if (query != null) {
                    for (String q : query.split("&")) {
                        if (q.startsWith("token=")) {
                            token = q.substring("token=".length());
                            // strip possible "Bearer " prefix if client included it in query
                            if (token.startsWith("Bearer ")) {
                                token = token.substring(7);
                            }
                            break;
                        }
                    }
                }
            }

            if (token == null || token.isBlank()) {
                return null;
            }

            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.getSubject();
            if (username == null || username.isBlank()) return null;

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Principal name: if it's a Customer, use customerID as principal name so NotificationService.convertAndSendToUser(customerId.toString(),...) matches
            Principal principal = null;
            if (userDetails instanceof CustomerDetails) {
                Long cid = ((CustomerDetails) userDetails).getCustomerID();
                if (cid != null) {
                    principal = () -> cid.toString();
                }
            }

            if (principal == null) {
                // fallback to username
                principal = () -> userDetails.getUsername();
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            // attach a Principal wrapper that exposes desired name when requested by messaging
            final Principal finalPrincipal = principal;

            return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), null, auth.getAuthorities()) {
                @Override
                public String getName() {
                    return finalPrincipal.getName();
                }
            };
        } catch (Exception e) {
            // ignore and return null principal
            System.err.println("WS handshake auth failed: " + e.getMessage());
            return null;
        }
    }
}
