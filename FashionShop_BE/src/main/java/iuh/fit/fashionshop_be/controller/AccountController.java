/*
 * @ (#) f.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */

import iuh.fit.fashionshop_be.model.Account;
import iuh.fit.fashionshop_be.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    //    @GetMapping("/accounts/email/{email}")
//    public ResponseEntity<Optional<Account>> getAccountByEmail(@PathVariable String email) {
//        return ResponseEntity.ok(accountService.getAccountByEmail(email));
//    }
    //hung
    @GetMapping("/accounts/email/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
        System.out.println("📩 Nhận yêu cầu lấy account theo email: " + email);
        Optional<Account> accountOpt = accountService.getAccountByEmail(email);

        if (accountOpt.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy account!");
            return ResponseEntity.notFound().build();
        }

        Account account = accountOpt.get();

        // Nếu có liên kết Customer → thêm log kiểm tra
        if (account.getCustomer() != null) {
            System.out.println("👤 Tên người dùng: " + account.getCustomer().getFullName());
        }

        return ResponseEntity.ok(account);
    }


    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.status(201).body(accountService.createAccount(account));
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        return ResponseEntity.ok(accountService.updateAccount(id, accountDetails));
    }
}