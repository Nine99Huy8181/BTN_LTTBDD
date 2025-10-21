/*
 * @ (#) AuthController.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.JwtResponse;
import iuh.fit.fashionshop_be.dto.LoginRequest;
import iuh.fit.fashionshop_be.dto.RegisterRequest;
import iuh.fit.fashionshop_be.dto.UserResponse;
import iuh.fit.fashionshop_be.model.Account;
import iuh.fit.fashionshop_be.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private static final String SECRET_KEY = "your-very-long-secret-key-at-least-32-characters-long-for-hs256";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public AuthController(AuthenticationManager authenticationManager, AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Authenticate với email
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

            // LẤY EMAIL ĐẦY ĐỦ TỪ AUTHENTICATION (không dùng request.getUserName())
            String email = authentication.getName();

            System.out.println("Login successful - Email: " + email); // Debug

            String jwt = Jwts.builder()
                    .setSubject(email)  // ← LƯU EMAIL ĐẦY ĐỦ
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(key)
                    .compact();

            System.out.println("Generated JWT subject: " +
                    Jwts.parser().verifyWith(key).build()
                            .parseSignedClaims(jwt).getPayload().getSubject()); // Debug

            if (jwt.split("\\.").length != 3) {
                throw new IllegalStateException("Generated JWT is invalid");
            }

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            accountService.registerCustomer(request.getEmail(), request.getPassword(),
                    request.getFullName(), request.getPhoneNumber(),
                    request.getDateOfBirth(), request.getGender());
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            // Return the original exception message for clearer debugging
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .map(auth -> auth.replace("ROLE_", ""))
                    .orElse("CUSTOMER");

            // Trả về email đầy đủ thay vì userName rút gọn
            return ResponseEntity.ok(new UserResponse(authentication.getName(), role));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
    }
}