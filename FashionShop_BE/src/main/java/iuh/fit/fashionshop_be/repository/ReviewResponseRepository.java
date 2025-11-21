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
import iuh.fit.fashionshop_be.model.ReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewResponseRepository extends JpaRepository<ReviewResponse, Long> {
    ReviewResponse findByReviewReviewID(Long reviewID);

    //hung
    @Modifying
    @Query("DELETE FROM ReviewResponse r WHERE r.responseID = :id")
    void deleteByResponseID(@Param("id") Long id);
}