package com.code.lms.util;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
    private T data;
    private ApiResponse(HttpStatus httpStatus,int statusCode, String message) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;

    }
    private ApiResponse(HttpStatus httpStatus,int statusCode, String message, T data) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // Static method for success responses
    public static <T> ApiResponse<T> success(HttpStatus httpStatus,int statusCode, String message, T data) {
        return new ApiResponse<>(httpStatus,statusCode, message, data);
    }
    public static <T> ApiResponse<T> success(HttpStatus httpStatus,int statusCode, String message) {
        return new ApiResponse<>(httpStatus,statusCode, message);
    }

    // Static method for error responses (without data)
    public static <T> ApiResponse<T> error(HttpStatus httpStatus,int statusCode, String message) {
        return new ApiResponse<>(httpStatus,statusCode, message, null);
    }

    // Static method for error responses (with data)
    public static <T> ApiResponse<T> error(HttpStatus httpStatus,int statusCode, String message, T data) {
        return new ApiResponse<>(httpStatus,statusCode, message, data);
    }

    // Getters and setters

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}