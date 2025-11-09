package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.request.PaymentRequest;
import iuh.fit.fashionshop_be.dto.response.PaymentResponse;
import iuh.fit.fashionshop_be.enums.PaymentStatus;
import iuh.fit.fashionshop_be.service.OrderService;
import iuh.fit.fashionshop_be.service.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final VnPayService vnPayService;
    private final OrderService orderService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(HttpServletRequest request,
                                           @RequestBody PaymentRequest paymentRequest) {
        long amount = paymentRequest.getAmount();
        String orderInfo = paymentRequest.getOrderInfo();

        String vnpayUrl = vnPayService.createPaymentUrl(request, amount, orderInfo);
        return ResponseEntity.ok(new PaymentResponse(vnpayUrl));
    }

    @GetMapping("/vnpay-return")
    public void handleVnpayReturn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("📱 VNPay callback received");

        int status = vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String orderId = extractOrderIdFromOrderInfo(orderInfo);

        System.out.println("📱 Payment status: " + status + ", orderId: " + orderId);

        // Cập nhật trạng thái thanh toán trong database
        try {
            if (status == 1) {
                orderService.updatePaymentStatus(Long.parseLong(orderId), PaymentStatus.PAID);
                System.out.println("✅ Updated order " + orderId + " PAID");
            } else {
                orderService.updatePaymentStatus(Long.parseLong(orderId), PaymentStatus.PENDING);
                System.out.println("❌ Updated order " + orderId + " PENDING");
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error updating order status: " + e.getMessage());
        }

        // Render HTML page với deep link
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String statusParam = status == 1 ? "success" : "failed";
        String deepLink = "fashionapp://payment-return?status=" + statusParam + "&orderId=" + orderId;

        // ===== BẮT ĐẦU HTML =====
        out.println("<!DOCTYPE html>");
        out.println("<html lang='vi'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Kết quả thanh toán</title>");
        out.println("    <style>");
        out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("        body {");
        out.println("            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            min-height: 100vh;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            justify-content: center;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .container {");
        out.println("            background: white;");
        out.println("            border-radius: 24px;");
        out.println("            padding: 48px 32px;");
        out.println("            max-width: 420px;");
        out.println("            width: 100%;");
        out.println("            text-align: center;");
        out.println("            box-shadow: 0 20px 60px rgba(0,0,0,0.3);");
        out.println("            animation: slideUp 0.5s ease-out;");
        out.println("        }");
        out.println("        @keyframes slideUp {");
        out.println("            from { opacity: 0; transform: translateY(30px); }");
        out.println("            to { opacity: 1; transform: translateY(0); }");
        out.println("        }");
        out.println("        .icon-wrapper {");
        out.println("            width: 100px;");
        out.println("            height: 100px;");
        out.println("            margin: 0 auto 24px;");
        out.println("            border-radius: 50%;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            justify-content: center;");
        out.println("        }");
        out.println("        .success-icon { background: #10B981; }");
        out.println("        .error-icon { background: #EF4444; }");
        out.println("        .checkmark, .cross {");
        out.println("            font-size: 56px;");
        out.println("            color: white;");
        out.println("            font-weight: bold;");
        out.println("        }");
        out.println("        h1 {");
        out.println("            font-size: 28px;");
        out.println("            color: #1F2937;");
        out.println("            margin-bottom: 12px;");
        out.println("            font-weight: 700;");
        out.println("        }");
        out.println("        .order-number {");
        out.println("            font-size: 20px;");
        out.println("            color: #667eea;");
        out.println("            font-weight: 700;");
        out.println("            margin-bottom: 24px;");
        out.println("        }");
        out.println("        p {");
        out.println("            color: #6B7280;");
        out.println("            font-size: 15px;");
        out.println("            line-height: 1.6;");
        out.println("            margin-bottom: 32px;");
        out.println("        }");
        out.println("        .btn {");
        out.println("            display: block;");
        out.println("            width: 100%;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            color: white;");
        out.println("            padding: 16px 32px;");
        out.println("            border-radius: 12px;");
        out.println("            text-decoration: none;");
        out.println("            font-weight: 600;");
        out.println("            font-size: 16px;");
        out.println("            border: none;");
        out.println("            cursor: pointer;");
        out.println("        }");
        out.println("        .countdown {");
        out.println("            font-size: 14px;");
        out.println("            color: #9CA3AF;");
        out.println("            margin-top: 20px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");

        // Hiển thị nội dung theo status
        if (status == 1) {
            out.println("        <div class='icon-wrapper success-icon'>");
            out.println("            <div class='checkmark'>✓</div>");
            out.println("        </div>");
            out.println("        <h1>Thanh toán thành công!</h1>");
            out.println("        <p class='order-number'>#" + orderId + "</p>");
            out.println("        <p>Cảm ơn bạn đã mua sắm tại Fashion Store. Đơn hàng đang được xử lý.</p>");
        } else {
            out.println("        <div class='icon-wrapper error-icon'>");
            out.println("            <div class='cross'>✕</div>");
            out.println("        </div>");
            out.println("        <h1>Thanh toán thất bại</h1>");
            out.println("        <p class='order-number'>#" + orderId + "</p>");
            out.println("        <p>Đã có lỗi xảy ra. Vui lòng thử lại hoặc chọn phương thức thanh toán khác.</p>");
        }

        out.println("        <a href='#' class='btn' id='returnBtn'>Quay lại ứng dụng</a>");
        out.println("        <p class='countdown'>Tự động chuyển sau <span id='countdown'>3</span> giây</p>");
        out.println("    </div>");

        // ===== JAVASCRIPT - CHỈ MỘT ĐOẠN DUY NHẤT =====
        out.println("    <script>");
        out.println("        const deepLink = '" + deepLink + "';");
        out.println("        let count = 3;");
        out.println("        const countdownEl = document.getElementById('countdown');");
        out.println("        const returnBtn = document.getElementById('returnBtn');");
        out.println("        ");
        out.println("        console.log('🔗 Deep link:', deepLink);");
        out.println("        ");
        out.println("        // Function to attempt redirect");
        out.println("        function attemptRedirect() {");
        out.println("            console.log('📱 Attempting to open app...');");
        out.println("            ");
        out.println("            // Method 1: Try iframe");
        out.println("            const iframe = document.createElement('iframe');");
        out.println("            iframe.style.display = 'none';");
        out.println("            iframe.src = deepLink;");
        out.println("            document.body.appendChild(iframe);");
        out.println("            ");
        out.println("            // Method 2: Direct window.location");
        out.println("            setTimeout(() => {");
        out.println("                window.location.href = deepLink;");
        out.println("            }, 100);");
        out.println("            ");
        out.println("            // Fallback message");
        out.println("            setTimeout(() => {");
        out.println("                if (document.hasFocus()) {");
        out.println("                    countdownEl.parentElement.innerHTML = ");
        out.println("                        '<strong style=\"color: #667eea;\">Nhấn nút trên để mở ứng dụng</strong>';");
        out.println("                }");
        out.println("            }, 2000);");
        out.println("        }");
        out.println("        ");
        out.println("        // Countdown timer");
        out.println("        const interval = setInterval(() => {");
        out.println("            count--;");
        out.println("            countdownEl.textContent = count;");
        out.println("            ");
        out.println("            if (count <= 0) {");
        out.println("                clearInterval(interval);");
        out.println("                attemptRedirect();");
        out.println("            }");
        out.println("        }, 1000);");
        out.println("        ");
        out.println("        // Button click handler");
        out.println("        returnBtn.addEventListener('click', (e) => {");
        out.println("            e.preventDefault();");
        out.println("            clearInterval(interval);");
        out.println("            attemptRedirect();");
        out.println("        });");
        out.println("    </script>");
        out.println("</body>");
        out.println("</html>");

        out.close();
        System.out.println("📱 Rendered payment result page with deep link: " + deepLink);
    }

    @GetMapping("/check-status/{orderId}")
    public ResponseEntity<?> checkPaymentStatus(@PathVariable String orderId) {
        // TODO: Implement logic kiểm tra status của order trong database
        return ResponseEntity.ok().body("{\"status\": \"PENDING\"}");
    }

    private String extractOrderIdFromOrderInfo(String orderInfo) {
        if (orderInfo == null || orderInfo.isEmpty()) {
            return "unknown";
        }
        String[] parts = orderInfo.split(" ");
        return parts[parts.length - 1];
    }
}