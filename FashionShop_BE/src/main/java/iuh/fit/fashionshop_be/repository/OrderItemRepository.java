/*
 * @ (#) j.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderOrderID(Long orderID);
    List<OrderItem> findByVariantVariantID(Long variantID);

    //hung
    // Sản phẩm bán chạy nhất
    @Query("SELECT oi.variant.product.id as productId, " +
            "oi.variant.product.name as productName, " +
            "oi.variant.product.image as image, " +
            "SUM(oi.quantity) as totalSold, " +
            "SUM(oi.unitPrice * oi.quantity) as totalRevenue " +
            "FROM OrderItem oi " +
            "WHERE oi.order.orderStatus != 'CANCELLED' " +
            "GROUP BY oi.variant.product.id, oi.variant.product.name, oi.variant.product.image " +
            "ORDER BY totalSold DESC")
    List<Object[]> findBestSellingProducts(Pageable pageable);

    @EntityGraph(attributePaths = {"variant.product"})
    List<OrderItem> findByOrder_OrderID(Long orderId);
}