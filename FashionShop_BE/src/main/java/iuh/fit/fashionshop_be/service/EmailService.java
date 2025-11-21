/*
 * @ (#) f.java     1.0    21-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:21-Nov-25
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisService redisService;

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void sendOtpEmail(String toEmail) {
        String otp = generateOtp();
        redisService.saveOtp(toEmail, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Fashion Store - Mã xác minh đăng ký");
        message.setText("""
            Chào bạn!
            
            Mã xác minh tài khoản của bạn là:
            
            %s
            
            Mã này sẽ hết hạn sau 5 phút.
            
            Nếu bạn không yêu cầu, vui lòng bỏ qua email này.
            
            Trân trọng,
            Fashion Store Team
            """.formatted(otp));
        mailSender.send(message);
    }

    public boolean verifyOtp(String email, String otp) {
        boolean valid = redisService.isValidOtp(email, otp);
        if (valid) {
            redisService.deleteOtp(email); // dùng 1 lần là xóa
        }
        return valid;
    }
}