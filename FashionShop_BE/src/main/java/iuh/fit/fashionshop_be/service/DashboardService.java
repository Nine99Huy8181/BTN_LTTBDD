package iuh.fit.fashionshop_be.service;

import iuh.fit.fashionshop_be.dto.response.*;
import iuh.fit.fashionshop_be.model.Order;
import iuh.fit.fashionshop_be.model.Product;
import iuh.fit.fashionshop_be.model.Review;
import iuh.fit.fashionshop_be.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ReviewRepository reviewRepository;

    /**
     * Lấy thống kê tổng quan cho dashboard
     */
    public DashboardStatsDTO getDashboardStats() {
        return DashboardStatsDTO.builder()
                // Doanh thu (repositories trả BigDecimal cho các tổng, giữ nguyên)
                .totalRevenue(safeNull(orderRepository.calculateTotalRevenue()))
                .todayRevenue(safeNull(orderRepository.calculateTodayRevenue()))
                .monthRevenue(safeNull(orderRepository.calculateMonthRevenue()))
                .yearRevenue(safeNull(orderRepository.calculateYearRevenue()))

                // Đơn hàng
                .totalOrders(orderRepository.count())
                .newOrdersToday(orderRepository.countNewOrdersToday())
                .pendingOrders(orderRepository.countOrdersByStatus("PENDING"))
                .completedOrders(orderRepository.countOrdersByStatus("DELIVERED"))

                // Sản phẩm
                .totalProducts(productRepository.countTotalProducts())
                .lowStockProducts(productRepository.countLowStockProducts())

                // Khách hàng
                .totalCustomers(customerRepository.countTotalCustomers())
                .newCustomersThisMonth(customerRepository.countNewCustomersThisMonth())

                // Trung bình
                .averageOrderValue(safeDouble(orderRepository.calculateAverageOrderValue()))
                .averageRating(safeDouble(reviewRepository.calculateAverageRating()))
                .build();
    }

    /**
     * Lấy dữ liệu biểu đồ doanh thu
     */
    public RevenueChartDTO getRevenueChartData() {
        RevenueChartDTO chartDTO = new RevenueChartDTO();

        // Doanh thu 30 ngày gần nhất
        chartDTO.setDaily(getDailyRevenueData(30));

        // Doanh thu 12 tuần gần nhất
        chartDTO.setWeekly(getWeeklyRevenueData(12));

        // Doanh thu 12 tháng gần nhất
        chartDTO.setMonthly(getMonthlyRevenueData(12));

        // Doanh thu theo năm (5 năm gần nhất)
        chartDTO.setYearly(getYearlyRevenueData(5));

        return chartDTO;
    }

    /**
     * Lấy danh sách sản phẩm bán chạy
     */
    @Transactional(readOnly = true)
    public List<BestSellingProductDTO> getBestSellingProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Object[]> results = orderItemRepository.findBestSellingProducts(pageable);

        return results.stream().map(result -> {
            Long productId = result[0] instanceof Number ? ((Number) result[0]).longValue() : (Long) result[0];
            String productName = result[1] != null ? result[1].toString() : "";
            String imageUrl = result[2] != null ? result[2].toString() : "";
            Long totalSold = result[3] instanceof Number ? ((Number) result[3]).longValue() : 0L;
            BigDecimal totalRevenue = toBigDecimal(result[4]);

            Product product = productRepository.findById(productId).orElse(null);
            Double avgRating = 0.0;
            Integer stock = 0;
            if (product != null) {
                Double avg = calculateProductAverageRating(productId);
                avgRating = avg != null ? avg : 0.0;
                Integer s = calculateTotalStock(product);
                stock = s != null ? s : 0;
            }

            return BestSellingProductDTO.builder()
                    .productId(productId)
                    .productName(productName)
                    .imageUrl(imageUrl)
                    .totalSold(totalSold)
                    .totalRevenue(totalRevenue)
                    .averageRating(avgRating)
                    .stockQuantity(stock)
                    .build();
        }).collect(Collectors.toList());
    }

    /**
     * Lấy danh sách đơn hàng mới
     */
    @Transactional(readOnly = true)
    public List<RecentOrderDTO> getRecentOrders(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Order> orders = orderRepository.findRecentOrders(pageable);

        return orders.stream().map(order -> RecentOrderDTO.builder()
                .orderId(order.getOrderID())
                .orderNumber("ORD-" + order.getOrderID())
                .customerName(order.getCustomer() != null ? order.getCustomer().getFullName() : "")
                .customerEmail(order.getCustomer() != null && order.getCustomer().getAccount() != null
                        ? order.getCustomer().getAccount().getEmail() : "")
                .totalAmount(toBigDecimal(order.getTotalAmount()))
                .status(order.getOrderStatus())
                .paymentMethod(order.getPaymentMethod())
                .orderDate(order.getOrderDate())
                .itemCount(order.getOrderItems() != null ? order.getOrderItems().size() : 0)
                .build()
        ).collect(Collectors.toList());
    }

    /**
     * Lấy danh sách đánh giá mới
     */
    public List<RecentReviewDTO> getRecentReviews(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Review> reviews = reviewRepository.findRecentReviews(pageable);

        return reviews.stream().map(review -> RecentReviewDTO.builder()
                .reviewId(review.getReviewID())
                .productId(review.getProduct() != null ? review.getProduct().getProductID() : null)
                .productName(review.getProduct() != null ? review.getProduct().getName() : "")
                .productImage(review.getProduct() != null ? review.getProduct().getImage() : "")
                .customerName(review.getCustomer() != null ? review.getCustomer().getFullName() : "")
                .rating(review.getRating())
                .comment(review.getComment())
                .reviewDate(review.getReviewDate())
                .hasResponse(review.getResponse() != null)
                .build()
        ).collect(Collectors.toList());
    }

    // ==================== HELPER METHODS ====================

    private List<RevenueChartDTO.ChartDataPoint> getDailyRevenueData(int days) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        List<Object[]> results = orderRepository.findDailyRevenueData(startDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        return results.stream().map(result -> {
            String label = formatDateLabel(result[0], formatter);
            BigDecimal revenue = toBigDecimal(result[1]);
            long orderCount = result[2] instanceof Number ? ((Number) result[2]).longValue() : 0L;

            return RevenueChartDTO.ChartDataPoint.builder()
                    .label(label)
                    .revenue(revenue)
                    .orderCount(orderCount)
                    .build();
        }).collect(Collectors.toList());
    }

    private List<RevenueChartDTO.ChartDataPoint> getMonthlyRevenueData(int months) {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(months);
        List<Object[]> results = orderRepository.findMonthlyRevenueData(startDate);

        return results.stream().map(result -> {
            int year = result[0] instanceof Number ? ((Number) result[0]).intValue() : 0;
            int month = result[1] instanceof Number ? ((Number) result[1]).intValue() : 0;
            String label = String.format("%02d/%d", month, year);
            BigDecimal revenue = toBigDecimal(result[2]);
            long orderCount = result[3] instanceof Number ? ((Number) result[3]).longValue() : 0L;

            return RevenueChartDTO.ChartDataPoint.builder()
                    .label(label)
                    .revenue(revenue)
                    .orderCount(orderCount)
                    .build();
        }).collect(Collectors.toList());
    }

    private List<RevenueChartDTO.ChartDataPoint> getWeeklyRevenueData(int weeks) {
        LocalDateTime startDate = LocalDateTime.now().minusWeeks(weeks);
        List<Object[]> results = orderRepository.findWeeklyRevenueData(startDate);

        return results.stream().map(result -> {
            int year = result[0] instanceof Number ? ((Number) result[0]).intValue() : 0;
            int week = result[1] instanceof Number ? ((Number) result[1]).intValue() : 0;
            String label = String.format("W%02d/%d", week, year);
            BigDecimal revenue = toBigDecimal(result[2]);
            long orderCount = result[3] instanceof Number ? ((Number) result[3]).longValue() : 0L;

            return RevenueChartDTO.ChartDataPoint.builder()
                    .label(label)
                    .revenue(revenue)
                    .orderCount(orderCount)
                    .build();
        }).collect(Collectors.toList());
    }

    private List<RevenueChartDTO.ChartDataPoint> getYearlyRevenueData(int years) {
        LocalDateTime startDate = LocalDateTime.now().minusYears(years);
        List<Object[]> results = orderRepository.findYearlyRevenueData(startDate);

        return results.stream().map(result -> {
            int year = result[0] instanceof Number ? ((Number) result[0]).intValue() : 0;
            String label = String.valueOf(year);
            BigDecimal revenue = toBigDecimal(result[1]);
            long orderCount = result[2] instanceof Number ? ((Number) result[2]).longValue() : 0L;

            return RevenueChartDTO.ChartDataPoint.builder()
                    .label(label)
                    .revenue(revenue)
                    .orderCount(orderCount)
                    .build();
        }).collect(Collectors.toList());
    }

    private Double calculateProductAverageRating(Long productId) {
        Double avg = reviewRepository.calculateAverageRatingByProduct(productId);
        return avg != null ? avg : 0.0;
    }

    private Integer calculateTotalStock(Product product) {
        if (product == null || product.getVariants() == null) return 0;
        return product.getVariants().stream()
                .mapToInt(variant -> variant.getInventory() != null ? variant.getInventory().getQuantity() : 0)
                .sum();
    }

    // ==================== UTILS ====================

    private static BigDecimal safeNull(BigDecimal in) {
        return in == null ? BigDecimal.ZERO : in;
    }

    private static double safeDouble(Double d) {
        return d == null ? 0.0 : d;
    }

    /**
     * Convert an arbitrary object (Number, BigDecimal, Double, Float, Long, etc.)
     * to BigDecimal safely. If null or non-convertible -> BigDecimal.ZERO
     */
    private static BigDecimal toBigDecimal(Object obj) {
        if (obj == null) return BigDecimal.ZERO;
        if (obj instanceof BigDecimal) return (BigDecimal) obj;
        if (obj instanceof Number) {
            // Use valueOf(double) to support Double/Float/Long/Integer
            return BigDecimal.valueOf(((Number) obj).doubleValue());
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Format result[0] which may be java.sql.Date, java.sql.Timestamp, LocalDate, LocalDateTime, or String
     */
    private static String formatDateLabel(Object dateObj, DateTimeFormatter formatter) {
        if (dateObj == null) return "";
        if (dateObj instanceof java.sql.Date) {
            LocalDate ld = ((java.sql.Date) dateObj).toLocalDate();
            return ld.format(formatter);
        }
        if (dateObj instanceof java.sql.Timestamp) {
            LocalDateTime ldt = ((java.sql.Timestamp) dateObj).toLocalDateTime();
            return ldt.toLocalDate().format(formatter);
        }
        if (dateObj instanceof LocalDate) {
            return ((LocalDate) dateObj).format(formatter);
        }
        if (dateObj instanceof LocalDateTime) {
            return ((LocalDateTime) dateObj).toLocalDate().format(formatter);
        }
        // fallback: try parse from string
        try {
            String s = dateObj.toString();
            LocalDate parsed = LocalDate.parse(s);
            return parsed.format(formatter);
        } catch (Exception ignored) {
        }
        return dateObj.toString();
    }
}
