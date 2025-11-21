/*
 * @ (#) g.java     1.0    21-Nov-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
 
package iuh.fit.fashionshop_be.dto.request;
/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:21-Nov-25
 * @version: 1.0
 */

import lombok.Data;

@Data
public class VerifyOtpRequest{
    String email;
    String otp;
}