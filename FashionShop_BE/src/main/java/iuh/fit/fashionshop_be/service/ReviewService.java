/*
 * @ (#) f.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.model.Review;
import iuh.fit.fashionshop_be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(String id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<Review> getReviewsByProductId(String productID) {
        return reviewRepository.findByProductProductID(productID);
    }

    public List<Review> getReviewsByCustomerId(String customerID) {
        return reviewRepository.findByCustomerCustomerID(customerID);
    }

    public List<Review> getReviewsByStatus(String status) {
        return reviewRepository.findByStatus(status);
    }

    public Review createReview(Review review) {
        // Logic: update product averageRating
        return reviewRepository.save(review);
    }

    public Review updateReview(String id, Review reviewDetails) {
        Review review = getReviewById(id);
        review.setProduct(reviewDetails.getProduct());
        review.setCustomer(reviewDetails.getCustomer());
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        review.setReviewDate(reviewDetails.getReviewDate());
        review.setImages(reviewDetails.getImages());
        review.setStatus(reviewDetails.getStatus());
        return reviewRepository.save(review);
    }

    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }
}