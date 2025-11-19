package iuh.fit.fashionshop_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentOrderDTO {
    private Long orderId;
    private String orderNumber;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private String status;
    private String paymentMethod;
    private LocalDateTime orderDate;
    private Integer itemCount;
}