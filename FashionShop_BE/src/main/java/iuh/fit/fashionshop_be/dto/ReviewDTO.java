package iuh.fit.fashionshop_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long reviewID;
    private Long productID;
    private String productName;
    private Long customerID;
    private String customerName;
    private String customerAvatar;
    private Integer rating;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reviewDate;
    private String[] images;
    private String status;
    private ReviewResponseDTO response;
}