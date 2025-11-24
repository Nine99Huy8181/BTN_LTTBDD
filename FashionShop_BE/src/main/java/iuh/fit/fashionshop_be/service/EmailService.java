/*
 * @ (#) EmailService.java     1.0    22-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Random;

/*
 * @description: Email service using Resend API
 * @author: Nguyen Quoc Huy
 * @date: 22-Nov-25
 * @version: 2.0
 */
@Service
@RequiredArgsConstructor
public class EmailService {
    private final RedisService redisService;
    private final JavaMailSender mailSender;  // Thêm injection này

    @Value("${spring.mail.default-from}")  // Lấy from-email từ application.yml
    private String fromEmail;

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void sendOtpEmail(String toEmail) {
        String otp = generateOtp();
        redisService.saveOtp(toEmail, otp);

        // Tạo nội dung email
        String subject = "Fashion Store - Mã xác minh đăng ký";
        String emailBody = String.format("""
            Chào bạn!
           
            Mã xác minh tài khoản của bạn là:
           
            %s
           
            Mã này sẽ hết hạn sau 5 phút.
           
            Nếu bạn không yêu cầu, vui lòng bỏ qua email này.
           
            Trân trọng,
            Fashion Store Team
            """, otp);

        // Gửi email qua JavaMailSender
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(emailBody, false);  // false: text plain, true: HTML nếu cần
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending OTP email via Gmail SMTP", e);
        }
    }

    public boolean verifyOtp(String email, String otp) {
        boolean valid = redisService.isValidOtp(email, otp);
        if (valid) {
            redisService.deleteOtp(email); // dùng 1 lần là xóa
        }
        return valid;
    }
}