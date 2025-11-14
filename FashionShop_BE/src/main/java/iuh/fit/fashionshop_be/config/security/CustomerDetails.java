/*
 * @ (#) g.java     1.0    13-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.config.security;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:13-Nov-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomerDetails implements UserDetails {

    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Long customerID;          // <-- cần cho @PreAuthorize
    private final String role;              // giữ nguyên role gốc (SUPER, ADMIN, CUSTOMER)

    public CustomerDetails(Account account) {
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.role = account.getRole();
        this.authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + account.getRole())
        );
        // Some accounts (ADMIN, SUPER) may not have a Customer relation. Guard against NPE.
        if (account.getCustomer() != null) {
            this.customerID = account.getCustomer().getCustomerID();
        } else {
            this.customerID = null;
        }
    }

    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}