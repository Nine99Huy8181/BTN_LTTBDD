/*
 * @ (#) gh.java     1.0    21-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:21-Nov-25
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    private static final String OTP_PREFIX = "otp:register:";
    private static final long OTP_EXPIRE_MINUTES = 5;

    // Lưu OTP + tự động hết hạn
    public void saveOtp(String email, String otp) {
        String key = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(key, otp, OTP_EXPIRE_MINUTES, TimeUnit.MINUTES);
    }

    // Lấy OTP
    public String getOtp(String email) {
        return redisTemplate.opsForValue().get(OTP_PREFIX + email);
    }

    // Xóa OTP sau khi dùng xong
    public void deleteOtp(String email) {
        redisTemplate.delete(OTP_PREFIX + email);
    }

    // Kiểm tra còn tồn tại và đúng không
    public boolean isValidOtp(String email, String otp) {
        String stored = getOtp(email);
        return otp != null && stored != null && stored.equals(otp);
    }
}