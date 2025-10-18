/*
 * @ (#) f.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Account;
import iuh.fit.fashionshop_be.model.Admin;
import iuh.fit.fashionshop_be.model.Customer;
import iuh.fit.fashionshop_be.repository.AccountRepository;
import iuh.fit.fashionshop_be.repository.AdminRepository;
import iuh.fit.fashionshop_be.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(String id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account createAccount(Account account) {
        // Có thể thêm validate email unique
        return accountRepository.save(account);
    }

    public Account updateAccount(String id, Account accountDetails) {
        Account account = getAccountById(id);
        account.setEmail(accountDetails.getEmail());
        account.setPassword(accountDetails.getPassword());
        account.setRole(accountDetails.getRole());
        account.setRegistrationDate(accountDetails.getRegistrationDate());
        account.setAccountStatus(accountDetails.getAccountStatus());
        return accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    public Account registerCustomer(String email, String password, String fullName, String phoneNumber, LocalDate dateOfBirth, String gender) {
        if (accountRepository.findByEmail(email) != null) {
            System.out.println("Email already exists: " + email);
            throw new IllegalArgumentException("Email already exists");
        }
        Customer customer = Customer.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .build();
        customer = customerRepository.save(customer);
        System.out.println("Customer saved: " + customer.getCustomerID());

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password for " + email + ": " + encodedPassword);

        Account account = Account.builder()
                .email(email)
                .password(encodedPassword)
                .role("CUSTOMER")  // Hoặc "USER" nếu giữ nguyên
                .registrationDate(LocalDateTime.now())
                .accountStatus("ACTIVE")  // Giả định status mặc định
                .customer(customer)  // Liên kết với Customer
                .build();
        Account savedAccount = accountRepository.save(account);
        System.out.println("Account saved: " + savedAccount.getEmail());
        return savedAccount;
    }

    @Transactional
    public Account addAdmin(String email, String password, String fullName, String department, String position) {
        if (accountRepository.findByEmail(email) != null) {
            System.out.println("Email already exists: " + email);
            throw new IllegalArgumentException("Email already exists");
        }
        Admin admin = Admin.builder()
                .fullName(fullName)
                .department(department)
                .position(position)
                .hireDate(LocalDate.now())  // Set hireDate mặc định
                .build();
        admin = adminRepository.save(admin);
        System.out.println("Admin saved: " + admin.getAdminID());

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password for " + email + ": " + encodedPassword);

        Account account = Account.builder()
                .email(email)
                .password(encodedPassword)
                .role("ADMIN")
                .registrationDate(LocalDateTime.now())
                .accountStatus("ACTIVE")
                .admin(admin)  // Liên kết với Admin (giả định field 'admin' trong Account entity)
                .build();
        Account savedAccount = accountRepository.save(account);
        System.out.println("Account saved: " + savedAccount.getEmail());
        return savedAccount;
    }

    public boolean authenticate(String email, String password) {
        Optional<Account> account = accountRepository.findByEmail(email);
        boolean matches = account.isPresent() && passwordEncoder.matches(password, account.get().getPassword());
        System.out.println("Authentication for " + email + ": " + matches); // Log kết quả xác thực
        return matches;
    }
}