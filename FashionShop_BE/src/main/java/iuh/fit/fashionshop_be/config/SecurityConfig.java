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
                        // 1. Public endpoints
                        .requestMatchers("/api/auth/**",
                                "/api/categories",
                                "/api/products",
                                "/api/products/search",
                                "/api/variants",
                                "/api/variants/product/{id}",
                                "/api/products/{id}",
                                "/api/products/category/{categoryId}",
                                "/api/products/brand/{brand}").permitAll()
// Khoa 
            // Allow customers to create orders (method-based rules before admin wildcard)
            .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("CUSTOMER")
            .requestMatchers(HttpMethod.GET, "/api/orders/customer/{customerId}").hasRole("CUSTOMER")

            // 2. SUPER endpoints - ĐẶT TRƯỚC CÁC ROLE KHÁC
            .requestMatchers("/api/accounts",           // GET /api/accounts
                "/api/accounts/**",          // CRUD operations
                "/api/admins/**",
                "/api/review-responses/**").hasRole("SUPER")

            // 3. ADMIN endpoints
            // Note: do NOT include "/api/customers/**" here because customer endpoints are authorized for CUSTOMER role below.
            .requestMatchers(
                "/api/admins",
                "/api/admins/{id}",
                "/api/addresses/**",
                "/api/products/**",
                "/api/variants/**",
                "/api/orders/**",
                "/api/order-items/**",
                "/api/coupons/**",
                "/api/categories/**",
                "/api/inventories/**",
                "/api/shippings/**").hasRole("ADMIN")

                        // 4. CUSTOMER endpoints - ĐẶT SAU CÙNG
                        .requestMatchers("/api/customers",
                                "/api/customers/{id}",
                                "/api/customers/account/{accountId}",
                                "/api/carts/{id}",
                                "/api/carts/customer/{customerId}",
                                "/api/cart-items",
                                "/api/cart-items/{id}",
                                "/api/cart-items/cart/{cartId}",
                                "/api/orders",
                                "/api/orders/{id}",
                                "/api/orders/customer/{customerId}",
                                "/api/order-items",
                                "/api/order-items/{id}",
                                "/api/order-items/order/{orderId}",
                                "/api/wishlists/{id}",
                                "/api/wishlists/customer/{customerId}",
                                "/api/wishlist-items",
                                "/api/wishlist-items/{id}",
                                "/api/wishlist-items/wishlist/{wishlistId}",
                                "/api/reviews",
                                "/api/reviews/{id}",
                                "/api/reviews/product/{productId}").hasRole("CUSTOMER")

                        // 5. Tất cả requests khác cần authenticated
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}