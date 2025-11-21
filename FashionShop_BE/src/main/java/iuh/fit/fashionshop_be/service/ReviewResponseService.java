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
import iuh.fit.fashionshop_be.model.ReviewResponse;
import iuh.fit.fashionshop_be.repository.ReviewResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewResponseService {
    private final ReviewResponseRepository responseRepository;

    public ReviewResponse getResponseById(Long id) {
        return responseRepository.findById(id).orElseThrow(() -> new RuntimeException("ReviewResponse not found"));
    }

    public ReviewResponse getResponseByReviewId(Long reviewID) {
        return responseRepository.findByReviewReviewID(reviewID);
    }

    public ReviewResponse createResponse(ReviewResponse response) {
        return responseRepository.save(response);
    }

    public ReviewResponse updateResponse(Long id, ReviewResponse responseDetails) {
        ReviewResponse response = getResponseById(id);
        response.setReview(responseDetails.getReview());
        response.setAdmin(responseDetails.getAdmin());
        response.setResponseContent(responseDetails.getResponseContent());
        response.setResponseDate(responseDetails.getResponseDate());
        response.setStatus(responseDetails.getStatus());
        return responseRepository.save(response);
    }

    @Transactional
    public void deleteResponse(Long id) {
        // Tìm và cập nhật Review trước
        ReviewResponse response = responseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReviewResponse not found"));

        Review review = response.getReview();
        if (review != null) {
            review.setResponse(null);
        }

        responseRepository.deleteByResponseID(id);
    }
}