/*
 * @ (#) d.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.controller;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */

import iuh.fit.fashionshop_be.dto.ReviewDTO;
import iuh.fit.fashionshop_be.model.Review;
import iuh.fit.fashionshop_be.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //    @GetMapping("/reviews")
//    public ResponseEntity<List<Review>> getAllReviews() {
//        return ResponseEntity.ok(reviewService.getAllReviews());
//    }
//
//    @GetMapping("/reviews/{id}")
//    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
//        return ResponseEntity.ok(reviewService.getReviewById(id));
//    }
//
//    @GetMapping("/reviews/product/{productId}")
//    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
//        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
//    }
    //hung
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewDTOById(id));
    }

    @GetMapping("/reviews/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductIdDTO(productId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO reviewDTO,
                                               @AuthenticationPrincipal UserDetails principal) {
        // principal should be non-null because endpoint is protected; extra check to be safe
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }
        try {
            Review saved = reviewService.createReview(reviewDTO, principal.getUsername());
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDetails));
    }

    @GetMapping("/reviews/check-eligible/{customerId}/{productId}")
    public ResponseEntity<Boolean> canReviewProduct(@PathVariable Long customerId, @PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.canReviewProduct(customerId, productId));
    }
}