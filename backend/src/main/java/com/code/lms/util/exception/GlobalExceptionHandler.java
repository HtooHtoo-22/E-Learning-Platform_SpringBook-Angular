package com.code.lms.util.exception;

import com.code.lms.util.ApiResponse;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidEmailException(InvalidEmailException ex) {
        ApiResponse<?> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiResponse<?>> handleIncorrectException(IncorrectPasswordException ex){
        ApiResponse<?> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFoundException(NotFoundException ex){
        ApiResponse<?> errorResponse = ApiResponse.error(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

}
