/*
 * @ (#) AppException.java     1.0    24-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.exception;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:24-Oct-25
 * @version: 1.0
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // Allow creating AppException with a custom message while keeping an ErrorCode
    public AppException(ErrorCode errorCode, String message) {
        super(message != null ? message : errorCode.getMessage());
        this.errorCode = errorCode;
    }
}