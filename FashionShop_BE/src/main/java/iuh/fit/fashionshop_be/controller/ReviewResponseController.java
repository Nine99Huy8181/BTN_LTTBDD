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
import iuh.fit.fashionshop_be.model.ReviewResponse;
import iuh.fit.fashionshop_be.service.ReviewResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewResponseController {

    private final ReviewResponseService responseService;

    public ReviewResponseController(ReviewResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/review-responses/{id}")
    public ResponseEntity<ReviewResponse> getResponseById(@PathVariable String id) {
        return ResponseEntity.ok(responseService.getResponseById(id));
    }

    @GetMapping("/review-responses/review/{reviewId}")
    public ResponseEntity<ReviewResponse> getResponseByReviewId(@PathVariable String reviewId) {
        return ResponseEntity.ok(responseService.getResponseByReviewId(reviewId));
    }

    @PostMapping("/review-responses")
    public ResponseEntity<ReviewResponse> createResponse(@RequestBody ReviewResponse response) {
        return ResponseEntity.status(201).body(responseService.createResponse(response));
    }

    @PutMapping("/review-responses/{id}")
    public ResponseEntity<ReviewResponse> updateResponse(@PathVariable String id, @RequestBody ReviewResponse responseDetails) {
        return ResponseEntity.ok(responseService.updateResponse(id, responseDetails));
    }
}