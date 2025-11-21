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

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO reviewDTO,
                                               @AuthenticationPrincipal UserDetails principal) {
        // principal should be non-null because endpoint is protected; extra check to be safe
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }
        Review saved = reviewService.createReview(reviewDTO, principal.getUsername());
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDetails));
    }
}