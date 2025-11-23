package iuh.fit.fashionshop_be.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VnPayService {

    // Inject these from application.properties or yml
    @Value("${vnpay.tmnCode}")
    private String vnp_TmnCode;

    @Value("${vnpay.hashSecret}")
    private String vnp_HashSecret;

    @Value("${vnpay.url}")
    private String vnp_PayUrl;

    @Value("${vnpay.returnUrl}")
    private String vnp_ReturnUrl;

    public String createPaymentUrl(HttpServletRequest req, long amount, String orderInfo) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = getRandomNumber(8);
        String vnp_IpAddr = getIpAddress(req);

        amount = amount * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        //Sử dụng timezone Việt Nam
        TimeZone vietnamTimeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        Calendar cld = Calendar.getInstance(vietnamTimeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(vietnamTimeZone);
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // Build data to hash and querystring
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(urlEncode(fieldValue));

                // Build query
                query.append(urlEncode(fieldName));
                query.append('=');
                query.append(urlEncode(fieldValue));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        String finalUrl = vnp_PayUrl + "?" + queryUrl;
        System.out.println("🔗 Final VNPay URL: " + finalUrl);
        System.out.println("📝 Hash Data: " + hashData.toString());
        System.out.println("🔐 Secure Hash: " + vnp_SecureHash);

        return finalUrl;
    }

    // Helper method for URL encoding - MUST USE UTF-8 for VNPAY
    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

    // Utility methods from Config
    private String getRandomNumber(int length) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        // For local development, use a valid IP
        if (ipAddress == null || ipAddress.isEmpty() || ipAddress.equals("0:0:0:0:0:0:0:1")) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }

    private String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final javax.crypto.Mac hmac512 = javax.crypto.Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final javax.crypto.spec.SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            System.err.println("HMAC SHA512 Error: " + ex.getMessage());
            return "";
        }
    }

    public int orderReturn(HttpServletRequest request) {
        System.out.println("=== VNPay Return Debug ===");

        Map<String, String> fields = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();

        // Log all parameters for debugging
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            fields.put(paramName, paramValue);
            System.out.println("📋 " + paramName + " = " + paramValue);
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        System.out.println("Received SecureHash: " + vnp_SecureHash);

        // Remove hash fields before signing
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        String signValue = hashAllFields(fields);
        System.out.println("Calculated SecureHash: " + signValue);

        boolean hashValid = signValue.equals(vnp_SecureHash);
        System.out.println("Hash validation: " + hashValid);

        if (hashValid) {
            String transactionStatus = request.getParameter("vnp_TransactionStatus");
            String responseCode = request.getParameter("vnp_ResponseCode");
            System.out.println("Transaction Status: " + transactionStatus);
            System.out.println("Response Code: " + responseCode);

            if ("00".equals(transactionStatus) && "00".equals(responseCode)) {
                System.out.println("Payment SUCCESS");
                return 1;
            } else {
                System.out.println("Payment FAILED - Status: " + transactionStatus + ", Response: " + responseCode);
                return 0;
            }
        } else {
            System.out.println("INVALID SIGNATURE");
            return 0;
        }
    }

    private String hashAllFields(Map<String, String> fields) {
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();

        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(urlEncode(fieldValue));
                if (itr.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return hmacSHA512(vnp_HashSecret, sb.toString());
    }
}