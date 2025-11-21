/*
 * @ (#) h.java     1.0    17-Oct-25
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
import iuh.fit.fashionshop_be.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductProductID(Long productID);
    List<ProductVariant> findBySku(String sku);
    @Query("SELECT COALESCE(i.quantity - i.reservedQuantity, 0) " +
            "FROM Inventory i " +
            "WHERE i.variant.variantID = :variantId")
    Integer getAvailableStockByVariant(@Param("variantId") Long variantId);

    @Query("SELECT COALESCE(i.reservedQuantity, 0) " +
            "FROM Inventory i " +
            "WHERE i.variant.variantID = :variantId")
    Integer getReservedQuantityByVariant(@Param("variantId") Long variantId);

}