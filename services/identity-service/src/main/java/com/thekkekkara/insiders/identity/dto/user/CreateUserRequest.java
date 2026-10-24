package com.thekkekkara.insiders.identity.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

                @NotBlank(message = "The username is required") @Size(max = 100, message = "The username must not exceed 100 characters") String usernameString,

                @NotBlank(message = "The email is required") @Email(message = "The email musst be valid") @Size(max = 255, message = "The email must not exceed 255 characters") String emailString,

                @NotBlank(message = "Password is required") @Size(min = 8, max = 72, message = "Password must be between 8 and 16 characters") String passwordString,

                @NotBlank(message = "First Name is required") @Size(max = 100, message = "First Name should not exceed 100 characters") String firstNameString,

                @NotBlank(message = "Last Name is required") @Size(max = 100, message = "Last Name should not exceed 100 characters") String lastNameString) {
}
