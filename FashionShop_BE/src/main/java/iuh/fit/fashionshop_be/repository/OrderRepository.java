/*
 * @ (#) f.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.repository;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // ✅ Custom query với JOIN FETCH để eager load orderItems
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.customer.customerID = :customerID ORDER BY o.orderDate DESC")
    List<Order> findByCustomerCustomerIDWithItems(@Param("customerID") Long customerID);
    
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems ORDER BY o.orderDate DESC")
    List<Order> findAllWithItems();
    
    // Original methods (giữ lại để backward compatible nếu cần)
    List<Order> findByCustomerCustomerID(Long customerID);
    List<Order> findByOrderStatus(String orderStatus);
    List<Order> findByPaymentStatus(String paymentStatus);
    Page<Order> findAllByOrderStatus(String status, Pageable pageable);


    //hung
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.orderStatus != 'CANCELLED'")
    BigDecimal calculateTotalRevenue();

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE DATE(o.orderDate) = CURRENT_DATE AND o.orderStatus != 'CANCELLED'")
    BigDecimal calculateTodayRevenue();

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE) AND YEAR(o.orderDate) = YEAR(CURRENT_DATE) AND o.orderStatus != 'CANCELLED'")
    BigDecimal calculateMonthRevenue();

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE) AND o.orderStatus != 'CANCELLED'")
    BigDecimal calculateYearRevenue();

    @Query("SELECT COUNT(o) FROM Order o WHERE DATE(o.orderDate) = CURRENT_DATE")
    Long countNewOrdersToday();

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = :status")
    Long countOrdersByStatus(@Param("status") String status);

    @Query("SELECT AVG(o.totalAmount) FROM Order o WHERE o.orderStatus != 'CANCELLED'")
    Double calculateAverageOrderValue();

    // Biểu đồ doanh thu theo ngày (30 ngày gần nhất)
    @Query("SELECT DATE(o.orderDate) as date, COALESCE(SUM(o.totalAmount), 0) as revenue, COUNT(o) as orderCount " +
            "FROM Order o WHERE o.orderDate >= :startDate AND o.orderStatus != 'CANCELLED' " +
            "GROUP BY DATE(o.orderDate) ORDER BY DATE(o.orderDate)")
    List<Object[]> findDailyRevenueData(@Param("startDate") LocalDateTime startDate);

    // Biểu đồ doanh thu theo tháng (12 tháng gần nhất)
    @Query("SELECT YEAR(o.orderDate) as year, MONTH(o.orderDate) as month, COALESCE(SUM(o.totalAmount), 0) as revenue, COUNT(o) as orderCount " +
            "FROM Order o WHERE o.orderDate >= :startDate AND o.orderStatus != 'CANCELLED' " +
            "GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
    List<Object[]> findMonthlyRevenueData(@Param("startDate") LocalDateTime startDate);

    // Đơn hàng mới nhất
    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    List<Order> findRecentOrders(Pageable pageable);

    // ✅ THÊM query này - Biểu đồ doanh thu theo tuần
    @Query("SELECT YEAR(o.orderDate) as year, WEEK(o.orderDate) as week, " +
            "COALESCE(SUM(o.totalAmount), 0) as revenue, COUNT(o) as orderCount " +
            "FROM Order o WHERE o.orderDate >= :startDate AND o.orderStatus != 'CANCELLED' " +
            "GROUP BY YEAR(o.orderDate), WEEK(o.orderDate) " +
            "ORDER BY YEAR(o.orderDate), WEEK(o.orderDate)")
    List<Object[]> findWeeklyRevenueData(@Param("startDate") LocalDateTime startDate);

    // ✅ THÊM query này - Biểu đồ doanh thu theo năm
    @Query("SELECT YEAR(o.orderDate) as year, COALESCE(SUM(o.totalAmount), 0) as revenue, " +
            "COUNT(o) as orderCount " +
            "FROM Order o WHERE o.orderDate >= :startDate AND o.orderStatus != 'CANCELLED' " +
            "GROUP BY YEAR(o.orderDate) ORDER BY YEAR(o.orderDate)")
    List<Object[]> findYearlyRevenueData(@Param("startDate") LocalDateTime startDate);
}