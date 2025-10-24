/*
 * @ (#) AuthController.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.request.LoginRequest;
import iuh.fit.fashionshop_be.dto.request.RegisterRequest;
import iuh.fit.fashionshop_be.dto.response.ApiResponse;
import iuh.fit.fashionshop_be.dto.response.JwtResponse;
import iuh.fit.fashionshop_be.dto.response.UserResponse;
import iuh.fit.fashionshop_be.exception.AppException;
import iuh.fit.fashionshop_be.exception.ErrorCode;
import iuh.fit.fashionshop_be.model.Account;
import iuh.fit.fashionshop_be.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final SecretKey key;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          AccountService accountService,
                          @Value("${jwt.secret}") String secretKey) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping("/login")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        String email = authentication.getName();
        String jwt = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000)) // 24h
                .signWith(key)
                .compact();

        return ApiResponse.<JwtResponse>builder()
                .code(1000)
                .message("Login successful")
                .result(new JwtResponse(jwt))
                .build();
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest request) {
        accountService.registerCustomer(
                request.getEmail(),
                request.getPassword(),
                request.getFullName(),
                request.getPhoneNumber(),
                request.getDateOfBirth(),
                request.getGender()
        );
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Registration successful")
                .result(null)
                .build();
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(auth -> auth.replace("ROLE_", ""))
                .orElse("CUSTOMER");

        UserResponse response = new UserResponse(authentication.getName(), role);
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("User info retrieved")
                .result(response)
                .build();
    }
}