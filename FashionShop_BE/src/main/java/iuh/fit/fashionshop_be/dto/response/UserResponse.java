package iuh.fit.fashionshop_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userName;
    private String role;
    private Long accountId;
    private Long customerId;

    // Constructor cũ để tương thích ngược
    public UserResponse(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }
}