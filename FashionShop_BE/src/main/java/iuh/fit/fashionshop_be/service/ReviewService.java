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
import iuh.fit.fashionshop_be.dto.ReviewDTO;
import iuh.fit.fashionshop_be.dto.ReviewResponseDTO;
import iuh.fit.fashionshop_be.model.*;
import iuh.fit.fashionshop_be.repository.AccountRepository;
import iuh.fit.fashionshop_be.repository.CustomerRepository;
import iuh.fit.fashionshop_be.repository.ProductRepository;
import iuh.fit.fashionshop_be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public ReviewDTO getReviewDTOById(Long id) {
        Review r = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return convertToDTO(r);
    }

    //hung
    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO dto = ReviewDTO.builder()
                .reviewID(review.getReviewID())
                .productID(review.getProduct().getProductID())
                .productName(review.getProduct().getName())
                .customerID(review.getCustomer().getCustomerID())
                .customerName(review.getCustomer().getFullName())
                // Lấy avatar từ Account thông qua Customer
                .customerAvatar(review.getCustomer().getAccount() != null
                        ? review.getCustomer().getAccount().getAvatar()
                        : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .reviewDate(review.getReviewDate())
                .images(review.getImages())
                .status(review.getStatus())
                .build();

        // Thêm response nếu có
        if (review.getResponse() != null) {
            dto.setResponse(convertResponseToDTO(review.getResponse()));
        }

        return dto;
    }

    private ReviewResponseDTO convertResponseToDTO(ReviewResponse response) {
        return ReviewResponseDTO.builder()
                .responseID(response.getResponseID())
                .reviewID(response.getReview().getReviewID())
                .responseContent(response.getResponseContent())
                .responseDate(response.getResponseDate())
                .status(response.getStatus())
                .adminName(response.getAdmin().getAccount().getEmail()) // hoặc tên admin nếu có
                .build();
    }

    public List<Review> getReviewsByProductId(Long productID) {
        return reviewRepository.findByProductProductID(productID);
    }

    public List<Review> getReviewsByCustomerId(Long customerID) {
        return reviewRepository.findByCustomerCustomerID(customerID);
    }

    public List<Review> getReviewsByStatus(String status) {
        return reviewRepository.findByStatus(status);
    }

    // New API: create review using authenticated username (from token) and ReviewDTO from body
    public Review createReview(ReviewDTO dto, String username) {
        if (dto == null) throw new IllegalArgumentException("ReviewDTO is required");
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username is required");

        // 1. find account by username/email
        Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Account not found for username: " + username));

        // 2. find customer(s) by account id - assume one customer per account; take first
        List<Customer> customers = customerRepository.findByAccountAccountID(account.getAccountID());
        if (customers == null || customers.isEmpty()) {
            throw new RuntimeException("No customer associated with account: " + username);
        }
        Customer customer = customers.get(0);

        // 3. find product
        Product product = productRepository.findById(dto.getProductID())
                .orElseThrow(() -> new RuntimeException("Product not found: " + dto.getProductID()));

        // 4. build review
        Review review = new Review();
        review.setProduct(product);
        review.setCustomer(customer);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setImages(dto.getImages());
        review.setReviewDate(LocalDateTime.now());
        review.setStatus("ACTIVE");

        // 5. save
        return reviewRepository.save(review);
    }

    // Keep existing save that accepts full Review but prefer using above
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) {
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

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}