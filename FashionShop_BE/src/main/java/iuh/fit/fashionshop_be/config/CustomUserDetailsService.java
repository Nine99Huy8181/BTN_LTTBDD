/*
 * @ (#) CustomU.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.config;

import iuh.fit.fashionshop_be.model.Account;
import iuh.fit.fashionshop_be.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with email: " + email);

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("User not found: " + email);
                    return new UsernameNotFoundException("User not found: " + email);
                });

        System.out.println("User found: " + account.getEmail() + ", Role: " + account.getRole());

        // THÊM "ROLE_" PREFIX - CỰC KỲ QUAN TRỌNG!
        // Spring Security .hasRole() tự động thêm "ROLE_" prefix khi check
        // Nên authority phải là "ROLE_SUPER" để match với .hasRole("SUPER")
        String roleWithPrefix = "ROLE_" + account.getRole();
        System.out.println("Authority granted: " + roleWithPrefix); // Debug: xem có "ROLE_SUPER" không

        return new org.springframework.security.core.userdetails.User(
                account.getEmail(), // "sup"
                account.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleWithPrefix)) // "ROLE_SUPER"
        );
    }
}