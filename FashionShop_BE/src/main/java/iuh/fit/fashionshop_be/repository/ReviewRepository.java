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
import iuh.fit.fashionshop_be.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductProductID(Long productID);
    List<Review> findByCustomerCustomerID(Long customerID);
    List<Review> findByStatus(String status);

    //hung
    @Query("SELECT AVG(r.rating) FROM Review r")
    Double calculateAverageRating();

    @Query("SELECT r FROM Review r ORDER BY r.reviewDate DESC")
    List<Review> findRecentReviews(Pageable pageable);

    // Thêm method này vào ReviewRepository
    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM Review r WHERE r.product.productID = :productId")
    Double calculateAverageRatingByProduct(@Param("productId") Long productId);

}