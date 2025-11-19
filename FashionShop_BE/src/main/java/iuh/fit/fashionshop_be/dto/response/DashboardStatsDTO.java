package iuh.fit.fashionshop_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    // Doanh thu - sử dụng BigDecimal để tương thích với Float trong DB
    private BigDecimal totalRevenue;
    private BigDecimal todayRevenue;
    private BigDecimal monthRevenue;
    private BigDecimal yearRevenue;

    // Đơn hàng
    private Long totalOrders;
    private Long newOrdersToday;
    private Long pendingOrders;
    private Long completedOrders;

    // Sản phẩm
    private Long totalProducts;
    private Long lowStockProducts;

    // Khách hàng
    private Long totalCustomers;
    private Long newCustomersThisMonth;

    // Trung bình
    private Double averageOrderValue;
    private Double averageRating;
}