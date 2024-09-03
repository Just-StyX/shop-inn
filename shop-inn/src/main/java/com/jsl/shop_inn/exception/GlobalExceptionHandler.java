package com.jsl.shop_inn.exception;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException argumentNotValidException) {
        Set<String> errors = new HashSet<>();
        argumentNotValidException.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Internal error, contact admin")
                                .error(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(CustomerNotFoundException customerNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .error(customerNotFoundException.getMessage())
                                .businessErrorCode(ErrorCodes.CUSTOMER_NOT_FOUND.getCode())
                                .businessErrorDescription(ErrorCodes.CUSTOMER_NOT_FOUND.getDescription())
                                .build()
                );
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException lockedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ErrorCodes.ACCOUNT_LOCKED.getCode())
                                .businessErrorDescription(ErrorCodes.ACCOUNT_LOCKED.getDescription())
                                .error(lockedException.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException disabledException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ErrorCodes.ACCOUNT_DISABLED.getCode())
                                .businessErrorDescription(ErrorCodes.ACCOUNT_DISABLED.getDescription())
                                .error(disabledException.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException badCredentialsException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ErrorCodes.BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(ErrorCodes.BAD_CREDENTIALS.getDescription())
                                .error(ErrorCodes.BAD_CREDENTIALS.getDescription())
                                .build()
                );
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException messagingException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(messagingException.getMessage())
                                .build()
                );
    }
}
