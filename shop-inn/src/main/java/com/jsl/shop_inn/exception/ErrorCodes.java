package com.jsl.shop_inn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCodes {
    INVALID_DATA(101, "Bad input data", HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND(102, "What you are looking for does not exist", HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_FOUND(103, "Customer does not exist", HttpStatus.NOT_FOUND),
    READER_DOES_NOT_HAVE_PERMISSION(104, "Action requires authorization", HttpStatus.FORBIDDEN),
    TARGET_FOLDER_NOT_CREATE(105, "File folder not created", HttpStatus.EXPECTATION_FAILED),
    FILE_NOT_SAVE(106, "File not saved", HttpStatus.NOT_IMPLEMENTED),
    NO_FILE_FOUND(107, "No file in path", HttpStatus.NOT_FOUND),

    INCORRECT_CURRENT_PASSWORD(108, "Current password is not correct", HttpStatus.BAD_REQUEST),
    NEW_PASSWORD_DOES_NOT_MATCH(109, "New password does not match", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(110, "User account is locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED(111, "User account is disabled", HttpStatus.FORBIDDEN),
    BAD_CREDENTIALS(112,"Login username and / password is not correct", HttpStatus.FORBIDDEN);

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    ErrorCodes(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
