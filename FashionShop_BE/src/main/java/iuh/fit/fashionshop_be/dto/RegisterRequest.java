/*
 * @ (#) RegisterRequest.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.dto;

import lombok.*;

import java.time.LocalDate;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    String email;
    String password;
    String fullName;
    String phoneNumber;
    LocalDate dateOfBirth;
    String gender;
}