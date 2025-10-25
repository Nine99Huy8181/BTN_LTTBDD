package iuh.fit.fashionshop_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO dùng để nhận payload từ client khi tạo review.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long productID;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewDate;
    private String[] images;
}
