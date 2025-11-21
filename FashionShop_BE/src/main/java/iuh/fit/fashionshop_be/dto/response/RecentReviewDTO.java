package iuh.fit.fashionshop_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentReviewDTO {
    private Long reviewId;
    private Long productId;
    private String productName;
    private String productImage;
    private String customerName;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewDate;
    private Boolean hasResponse;
}