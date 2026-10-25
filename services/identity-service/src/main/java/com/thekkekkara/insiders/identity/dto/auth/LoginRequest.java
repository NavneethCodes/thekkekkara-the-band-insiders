package com.thekkekkara.insiders.identity.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String usernameString,

        @NotBlank String passwordString) {

}
