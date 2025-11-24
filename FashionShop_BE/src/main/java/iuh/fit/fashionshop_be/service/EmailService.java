/*
 * @ (#) EmailService.java     1.0    22-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.io.IOException;
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
    private final OkHttpClient httpClient = new OkHttpClient();

    @Value("${resend.api-key}")
    private String resendApiKey;

    @Value("${resend.from-email:onboarding@resend.dev}")
    private String fromEmail;

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void sendOtpEmail(String toEmail) {
        String otp = generateOtp();
        redisService.saveOtp(toEmail, otp);

        // Prepare email body with Resend API
        String emailBody = String.format("""
            Chào bạn!
            
            Mã xác minh tài khoản của bạn là:
            
            %s
            
            Mã này sẽ hết hạn sau 5 phút.
            
            Nếu bạn không yêu cầu, vui lòng bỏ qua email này.
            
            Trân trọng,
            Fashion Store Team
            """, otp);

        // Create JSON payload for Resend API
        JSONObject json = new JSONObject();
        json.put("from", fromEmail);
        json.put("to", new String[]{toEmail});
        json.put("subject", "Fashion Store - Mã xác minh đăng ký");
        json.put("text", emailBody);

        // Send HTTP request to Resend
        RequestBody body = RequestBody.create(
            json.toString(),
            MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
            .url("https://api.resend.com/emails")
            .addHeader("Authorization", "Bearer " + resendApiKey)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to send email: " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error sending OTP email via Resend", e);
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