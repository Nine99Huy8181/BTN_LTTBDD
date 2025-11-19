package iuh.fit.fashionshop_be.controller;

import iuh.fit.fashionshop_be.dto.response.*;
import iuh.fit.fashionshop_be.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // Chỉ admin mới truy cập được
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * GET /api/admin/dashboard/stats
     * Lấy thống kê tổng quan
     */
    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    /**
     * GET /api/admin/dashboard/revenue-chart
     * Lấy dữ liệu biểu đồ doanh thu
     */
    @GetMapping("/revenue-chart")
    public ResponseEntity<RevenueChartDTO> getRevenueChart() {
        return ResponseEntity.ok(dashboardService.getRevenueChartData());
    }

    /**
     * GET /api/admin/dashboard/best-selling-products
     * Lấy danh sách sản phẩm bán chạy
     * @param limit số lượng sản phẩm muốn lấy (default: 10)
     */
    @GetMapping("/best-selling-products")
    public ResponseEntity<List<BestSellingProductDTO>> getBestSellingProducts(
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(dashboardService.getBestSellingProducts(limit));
    }

    /**
     * GET /api/admin/dashboard/recent-orders
     * Lấy danh sách đơn hàng mới
     * @param limit số lượng đơn hàng muốn lấy (default: 10)
     */
    @GetMapping("/recent-orders")
    public ResponseEntity<List<RecentOrderDTO>> getRecentOrders(
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(dashboardService.getRecentOrders(limit));
    }

    /**
     * GET /api/admin/dashboard/recent-reviews
     * Lấy danh sách đánh giá mới
     * @param limit số lượng đánh giá muốn lấy (default: 10)
     */
    @GetMapping("/recent-reviews")
    public ResponseEntity<List<RecentReviewDTO>> getRecentReviews(
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(dashboardService.getRecentReviews(limit));
    }
}