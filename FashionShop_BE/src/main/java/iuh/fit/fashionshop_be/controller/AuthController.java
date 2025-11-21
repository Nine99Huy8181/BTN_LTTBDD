package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.request.LoginRequest;
import iuh.fit.fashionshop_be.dto.request.RegisterRequest;
import iuh.fit.fashionshop_be.dto.request.ResetPasswordRequest;
import iuh.fit.fashionshop_be.dto.request.VerifyOtpRequest;
import iuh.fit.fashionshop_be.dto.response.ApiResponse;
import iuh.fit.fashionshop_be.dto.response.JwtResponse;
import iuh.fit.fashionshop_be.dto.response.UserResponse;
import iuh.fit.fashionshop_be.exception.AppException;
import iuh.fit.fashionshop_be.exception.ErrorCode;
import iuh.fit.fashionshop_be.model.Account;
import iuh.fit.fashionshop_be.repository.AccountRepository;
import iuh.fit.fashionshop_be.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import iuh.fit.fashionshop_be.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * AuthController — single clean implementation
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final SecretKey key;
    private final EmailService emailService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          AccountService accountService,
                          @Value("${jwt.secret}") String secretKey, EmailService emailService, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.emailService = emailService;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest request) {
        try {
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

        } catch (UsernameNotFoundException | DisabledException e) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } catch (BadCredentialsException e) {
            throw new AppException(ErrorCode.INVALID_ACCOUNT);
        } catch (AuthenticationException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
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

        String email = authentication.getName();

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(auth -> auth.replace("ROLE_", ""))
                .orElse("CUSTOMER");

        Long accountId = null;
        Long customerId = null;
        try {
            accountId = accountService.getAccountByEmail(authentication.getName())
                    .map(Account::getAccountID)
                    .orElse(null);
        } catch (Exception ignored) {
        }

        UserResponse response = new UserResponse(accountId, authentication.getName(), role);


        try {
            // Lấy Account từ email
            Account account = accountService.getAccountByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            accountId = account.getAccountID();

            // Nếu là CUSTOMER, lấy customerId từ quan hệ OneToOne
            if ("CUSTOMER".equals(role) && account.getCustomer() != null) {
                customerId = account.getCustomer().getCustomerID();
            }
        } catch (Exception e) {
            System.err.println("Error fetching customer info: " + e.getMessage());
        }

        response = new UserResponse(email, role, accountId, customerId);

        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("User info retrieved")
                .result(response)
                .build();
    }

    @PostMapping("/send-otp")
    public ApiResponse<String> sendOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        if (accountRepository.findByEmail(email).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        emailService.sendOtpEmail(email);

        return ApiResponse.<String>builder()
                .code(1000)
                .message("OTP đã được gửi đến email của bạn")
                .build();
    }

    @PostMapping("/verify-otp")
    public ApiResponse<String> verifyOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = body.get("otp");

        if (emailService.verifyOtp(email, otp)) {
            return ApiResponse.<String>builder()
                    .code(1000)
                    .message("Xác minh thành công")
                    .build();
        } else {
            throw new AppException(ErrorCode.INVALID_REQUEST, "Mã OTP không đúng hoặc đã hết hạn");
        }
    }


    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        if (accountRepository.findByEmail(email).isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        emailService.sendOtpEmail(email); // dùng chung service gửi OTP đăng ký

        return ApiResponse.<String>builder()
                .code(1000)
                .message("Mã OTP đã được gửi đến email của bạn")
                .build();
    }

    // 2. Xác minh OTP
    @PostMapping("/verify-otp-forgot")
    public ApiResponse<String> verifyOtpForgot(@RequestBody VerifyOtpRequest req) {
        if (emailService.verifyOtp(req.getEmail(), req.getOtp())) {
            return ApiResponse.<String>builder()
                    .code(1000)
                    .message("Xác minh thành công")
                    .build();
        }
        throw new AppException(ErrorCode.INVALID_OTP);
    }

    // 3. Đặt lại mật khẩu mới
    @PostMapping("/reset-password")
    public ApiResponse<String> resetPassword(@RequestBody ResetPasswordRequest req) {
        if (!req.getNewPassword().equals(req.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        Account account = accountRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

//        // Kiểm tra OTP lần cuối trước khi đổi mk
//        if (!emailService.verifyOtp(req.getEmail(), req.getOtp())) {
//            throw new AppException(ErrorCode.INVALID_OTP);
//        }

        account.setPassword(passwordEncoder.encode(req.getNewPassword()));
        accountRepository.save(account);

        return ApiResponse.<String>builder()
                .code(1000)
                .message("Đặt lại mật khẩu thành công")
                .build();
    }
}