package iuh.fit.fashionshop_be.dto.response;

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

    public UserResponse(Long accountId, String userName, String role) {
        this.accountId = accountId;
        this.userName = userName;
        this.role = role;
    }
}