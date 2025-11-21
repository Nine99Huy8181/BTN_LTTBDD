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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductID(Long productID);
    List<Product> findByCategoryCategoryID(Long categoryID);
    List<Product> findByBrand(String brand);
    List<Product> findByStatus(String status);

    //Huy
    @Query("SELECT COALESCE(SUM(oi.quantity), 0) " +
            "FROM OrderItem oi " +
            "JOIN oi.variant pv " +
            "JOIN oi.order o " +
            "WHERE pv.product.productID = :productId " +
            "AND o.orderStatus = 'DELIVERED'")
    Integer getSoldQuantity(@Param("productId") Long productId);
    @Query("SELECT p FROM Product p WHERE " +
            "(:keyword IS NULL OR (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
            "AND (:minPrice IS NULL OR p.basePrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.basePrice <= :maxPrice) " +
            "AND (:minRating IS NULL OR p.averageRating >= :minRating) " +
            "AND (:maxRating IS NULL OR p.averageRating <= :maxRating)")
    List<Product> searchProducts(
            @Param("keyword") String keyword,
            @Param("minPrice") Float minPrice,
            @Param("maxPrice") Float maxPrice,
            @Param("minRating") Float minRating,
            @Param("maxRating") Float maxRating
    );
    @Query("""
        SELECT p FROM Product p
        WHERE p.status = 'ACTIVE'
        ORDER BY p.reviewCount DESC, p.averageRating DESC
        """)
    Page<Product> findTopSellingActiveProducts(Pageable pageable);

    // Sản phẩm active ngẫu nhiên
    @Query(value = "SELECT * FROM products WHERE Status = 'ACTIVE' ORDER BY RAND()", nativeQuery = true)
    Page<Product> findRandomActiveProducts(Pageable pageable);

    // Hoặc dùng JPQL + function("RAND") nếu không muốn native
    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE' ORDER BY function('RAND')")
    Page<Product> findRandomActiveProductsJPQL(Pageable pageable);

    //hung
    @Query("SELECT COUNT(p) FROM Product p")
    Long countTotalProducts();

    // Sản phẩm sắp hết hàng (tổng inventory < 10)
    @Query("SELECT COUNT(DISTINCT p) FROM Product p JOIN p.variants pv JOIN pv.inventory i WHERE i.quantity < 10")
    Long countLowStockProducts();
}