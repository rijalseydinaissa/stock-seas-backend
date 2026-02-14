package com.example.stock_saas.shared.api.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Standardized API response wrapper.
 * All API endpoints should return responses in this format for consistency.
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final List <ErrorDetail> errors;
    private final Map<String, Object> metadata;

    @Builder.Default
    private final Instant timestamp = Instant.now();

    /**
     * Create a successful response with data
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }
    /**
     * Create a successful response with data and message
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .build();
    }

    /**
     * Create a successful response with only a message
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .build();
    }
    /**
     * Create an error response with a single error message
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
    /**
     * Create an error response with error details
     */
    public static <T> ApiResponse<T> error(String message, List<ErrorDetail> errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
    /**
     * Create an error response with a single error detail
     */
    public static <T> ApiResponse<T> error(String message, ErrorDetail error) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(List.of(error))
                .build();
    }
    /**
     * Error detail for validation or business errors
     */
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ErrorDetail(String message, int code, String field, Object rejectedValue) {
    }
}
