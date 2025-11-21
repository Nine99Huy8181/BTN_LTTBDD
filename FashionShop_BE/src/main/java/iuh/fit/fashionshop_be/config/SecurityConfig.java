/*
 * @ (#) g.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.config;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                                // ==============================================================
                                // 1. PUBLIC ENDPOINTS – AI CŨNG TRUY CẬP ĐƯỢC (không cần login)
                                // ==============================================================
                                .requestMatchers(
                                        "/api/auth/**",
                                        "/api/categories",
                                        "/api/categories/**",
                                        "/api/products",
                                        "/api/products/**",
                                        "/api/products/search",
                                        "/api/products/category/{categoryId}",
                                        "/api/products/br   and/{brand}",
                                        "/api/variants",
                                        "/api/variants/**",
                                        "/api/chat",
                                        "/api/variants/product/{id}",
                                        "/api/payment/create-payment",
                                        "/api/payment/vnpay-return",
                                        "/ws/**",
                                        "/api/reviews/**"
                                ).permitAll()

                                // ==============================================================
                                // 2. SUPER ADMIN – QUYỀN CAO NHẤT (phải đặt TRƯỚC)
                                // ==============================================================
//                        .requestMatchers(
//                                "/api/accounts",
//                                "/api/accounts/**",
//                                "/api/admins/**",
//                                "/api/review-responses/**"
//                        ).hasRole("SUPER")
                                //hung
                                .requestMatchers(
                                        "/api/accounts/email/**"
                                ).hasAnyRole("ADMIN", "CUSTOMER")

                                .requestMatchers(
                                        "/api/accounts",
                                        "/api/accounts/{id}",
                                        "/api/admins/**",
                                        "/api/review-responses/**"
                                ).hasAnyRole("SUPER", "ADMIN")
                                //hung thêm cho ADMIN

                                // ==============================================================
                                // 3. ADMIN ONLY – CHỈ ADMIN (không cho Customer)
                                // ==============================================================
                                .requestMatchers(
                                        "/api/inventories/**",
                                        "/api/shippings/**",
                                        "/api/admins",
                                        "/api/admins/{id}"
                                ).hasRole("ADMIN")

                                // ==============================================================
                                // 4. CUSTOMER ONLY – CHỈ CUSTOMER (không cho Admin)
                                // ==============================================================
                                .requestMatchers(
//                                "/api/carts/{id}",
//                                "/api/carts/customer/{customerId}",
//                                "/api/cart-items",
//                                "/api/cart-items/{id}",
//                                "/api/cart-items/cart/{cartId}",
//                                "/api/wishlists/{id}",
//                                "/api/wishlists/customer/{customerId}",
//                                "/api/wishlist-items",
//                                "/api/wishlist-items/{id}",
//                                "/api/wishlist-items/wishlist/{wishlistId}",
//                                "/api/reviews",
//                                "/api/reviews/{id}",
//                                "/api/reviews/product/{productId}"
                                        "/api/carts/*",
                                        "/api/carts/customer/*",
                                        "/api/cart-items/**",
                                        "/api/cart-items/*",
                                        "/api/cart-items/cart/*",
                                        "/api/wishlists/*",
                                        "/api/wishlists/customer/*",
                                        "/api/wishlist-items/**",
                                        "/api/wishlist-items/*",
                                        "/api/wishlist-items/wishlist/*"
//                                "/api/reviews/**"
                                ).hasAnyRole("CUSTOMER", "ADMIN")
                                //hung thêm cho phép admin, sửa lại pattem cho gọn hơn

                                // ==============================================================
                                // 5. CẢ ADMIN & CUSTOMER ĐỀU DÙNG → authenticated() (phải login)
                                // ==============================================================
                                //hung
                                .requestMatchers(HttpMethod.DELETE, "/api/coupons/**").hasRole("ADMIN")

                                .requestMatchers(
                                        "/api/customers/{id}",
                                        "/api/customers/account/{accountId}",
                                        "/api/customers/**",           // Admin quản lý, Customer xem của mình
                                        "/api/addresses/**",
                                        "/api/coupons/**",
                                        "/api/orders/**",              // CẢ hai đều dùng: Customer đặt, Admin duyệt
                                        "/api/order-items/**"
                                ).authenticated()

                                // ==============================================================
                                // 6. RIÊNG: TẠO ĐƠN HÀNG – CHỈ CUSTOMER
                                // ==============================================================
                                .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("CUSTOMER")

                                // ==============================================================
                                // 7. TẤT CẢ CÁC REQUEST KHÁC → YÊU CẦU ĐĂNG NHẬP
                                // ==============================================================
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        // TẤT CẢ CÁC REQUEST → CHO PHÉP KHÔNG CẦN AUTH
//                        .anyRequest().permitAll()
//                )
//                // VẪN GIỮ LẠI FILTER ĐỂ JWT CÓ THỂ ĐƯỢC ĐỌC (nếu cần log user)
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
}