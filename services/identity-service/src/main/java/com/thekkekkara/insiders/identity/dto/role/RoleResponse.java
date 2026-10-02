package com.thekkekkara.insiders.identity.dto.role;

import java.time.LocalDateTime;

public record RoleResponse(
                Long roleIdLong,
                Long bandIdLong,
                String roleNameString,
                String roleDescriptionString,
                LocalDateTime createdAtLocalDateTime,
                LocalDateTime updatedAtLocalDateTime) {
}
