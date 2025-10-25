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
import iuh.fit.fashionshop_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryCategoryID(Long categoryID);
    List<Product> findByBrand(String brand);
    List<Product> findByStatus(String status);

    @Query("SELECT COALESCE(SUM(oi.quantity), 0) " +
            "FROM OrderItem oi " +
            "JOIN oi.variant pv " +
            "JOIN oi.order o " +
            "WHERE pv.product.productID = :productId " +
            "AND o.orderStatus = 'DELIVERED'")
    Integer getSoldQuantity(@Param("productId") Long productId);
}