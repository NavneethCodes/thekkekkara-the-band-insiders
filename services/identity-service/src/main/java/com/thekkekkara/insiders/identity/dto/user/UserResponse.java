package com.thekkekkara.insiders.identity.dto.user;

import java.time.LocalDateTime;

public record UserResponse(
                Long userIdLong,
                String usernameString,
                String emailString,
                String firstNameString,
                String lastNameString,
                Boolean enabledBoolean,
                Boolean accountLockedBoolean,
                LocalDateTime createdAtLocalDateTime,
                LocalDateTime updatedAtLocalDateTime) {
}
