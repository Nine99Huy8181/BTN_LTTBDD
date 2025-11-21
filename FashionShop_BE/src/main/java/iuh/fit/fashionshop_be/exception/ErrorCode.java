/*
 * @ (#) ErrorCode.java     1.0    24-Oct-25
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
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Email not exists", HttpStatus.BAD_REQUEST),
    INVALID_ACCOUNT(1004, "Email or password invalid", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    TOKEN_IS_NOT_VALID(1007, "Token is not valid", HttpStatus.UNAUTHORIZED),
    ENTITY_NOT_FOUND(2001, "Entity not found", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND(2004, "Role not found", HttpStatus.NOT_FOUND),
    ACCESS_DENIED(1008, "Access denied", HttpStatus.FORBIDDEN),
    INVALID_REQUEST(1009, "Invalid request", HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(1010, "Data integrity violation", HttpStatus.CONFLICT),
    INVENTORY_INSUFFICIENT(3001, "Insufficient inventory", HttpStatus.BAD_REQUEST),
    INVALID_OTP(3002, "Otp invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCHED(3003, "Password not match", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}