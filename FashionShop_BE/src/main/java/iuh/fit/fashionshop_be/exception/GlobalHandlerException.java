/*
 * @ (#) GlobalHandlerException.java     1.0    24-Oct-25
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
import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.fashionshop_be.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHandlerException {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        // Use the exception's message (may be a custom message) when available
        return buildResponse(errorCode, exception.getMessage()); // Sử dụng message từ exception(Khoa)
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAccessDeniedException(AccessDeniedException exception) {
        return buildResponse(ErrorCode.ACCESS_DENIED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAuthenticationException(AuthenticationException exception) {
        return buildResponse(ErrorCode.UNAUTHENTICATED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handlingHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return buildResponse(ErrorCode.INVALID_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handlingDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return buildResponse(ErrorCode.DATA_INTEGRITY_VIOLATION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handlingValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildResponse(ErrorCode.INVALID_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handlingGenericException(Exception exception) {
        exception.printStackTrace(); // Log để debug
        return buildResponse(ErrorCode.UNCATEGORIZED_EXCEPTION);
    }

    // Helper method
    private ResponseEntity<ApiResponse<Object>> buildResponse(ErrorCode errorCode) {
        return buildResponse(errorCode, errorCode.getMessage());
    }

    private ResponseEntity<ApiResponse<Object>> buildResponse(ErrorCode errorCode, String message) {
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(message)
                .result(null)
                .build();
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
}