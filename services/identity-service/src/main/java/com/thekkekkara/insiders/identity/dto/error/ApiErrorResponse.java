package com.thekkekkara.insiders.identity.dto.error;

import java.time.LocalDateTime;

public record ApiErrorResponse(
                int status,
                String error,
                String message,
                LocalDateTime timestamp) {
}