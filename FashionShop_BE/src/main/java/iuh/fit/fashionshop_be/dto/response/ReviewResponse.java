package iuh.fit.fashionshop_be.dto.response;

import iuh.fit.fashionshop_be.model.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewResponse {
    private Long reviewID;
    private Long productID;
    private Long customerID;
    private String customerName;
    private String customerAvatar;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewDate;
    private String[] images;
    private String status;

    public static ReviewResponse fromEntity(Review review) {
        ReviewResponse dto = new ReviewResponse();
        dto.setReviewID(review.getReviewID());
        dto.setProductID(review.getProduct().getProductID());
        dto.setCustomerID(review.getCustomer().getCustomerID());
        dto.setCustomerName(review.getCustomer().getFullName());
        dto.setCustomerAvatar(review.getCustomer().getAccount().getAvatar());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setReviewDate(review.getReviewDate());
        dto.setImages(review.getImages());
        dto.setStatus(review.getStatus());
        return dto;
    }
}