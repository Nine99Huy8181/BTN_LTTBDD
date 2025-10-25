/*
 * @ (#) UserResponse.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto.response;

import lombok.Data;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
@Data
public class UserResponse {
    private String userName;
    private String role;

    public UserResponse(String userName, String role) {
        this.userName = userName;
        this.role = role.replace("[", "").replace("]", "");
    }
}