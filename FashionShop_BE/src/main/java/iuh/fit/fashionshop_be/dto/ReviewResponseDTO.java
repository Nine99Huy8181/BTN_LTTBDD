

package iuh.fit.fashionshop_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDTO {
    private Long responseID;
    private Long reviewID;
    private String responseContent;
    private LocalDateTime responseDate;
    private String status;
    private String adminName;
}