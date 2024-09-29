package com.mad.machine.assessment.exception;

import com.mad.machine.assessment.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ConflictException
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ServiceResponse<String>> handleConflict(ConflictException ex) {
        ServiceResponse<String> errorResponse = new ServiceResponse<>(null, HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle custom ServiceException
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ServiceResponse<String>> handleServiceException(ServiceException ex) {
        ServiceResponse<String> errorResponse = new ServiceResponse<>(null, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions (e.g., generic runtime exceptions)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceResponse<String>> handleRuntimeException(RuntimeException ex) {
        ServiceResponse<String> errorResponse = new ServiceResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Fallback method for unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse<String>> handleGenericException(Exception ex) {
        ServiceResponse<String> errorResponse = new ServiceResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
