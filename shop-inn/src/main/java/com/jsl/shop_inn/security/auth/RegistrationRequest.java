package com.jsl.shop_inn.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank(message = "first name is required")
        @NotEmpty(message = "first name cannot be empty")
        String firstname,

        @NotBlank(message = "last name is required")
        @NotEmpty(message = "last name cannot be empty")
        String lastname,

        @NotBlank(message = "email is required")
        @NotEmpty(message = "email cannot be empty")
        @Email(message = "invalid email")
        String email,

        @Size(message = "password must be 8 characters or more")
        @NotBlank(message = "password is required")
        @NotEmpty(message = "password cannot be empty")
        String password
) {

}
