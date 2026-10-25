package com.thekkekkara.insiders.identity.domain;

import java.time.LocalDateTime;

public class Role {

    private final Long roleIdLong;
    private final Long bandIdLong;
    private final String roleNameString;
    private final String roleDescriptionString;
    private final LocalDateTime createdAtLocalDateTime;
    private final LocalDateTime updatedAtLocalDateTime;

    public Role(
            Long roleIdLong,
            Long bandIdLong,
            String roleNameString,
            String roleDescriptionString,
            LocalDateTime createdAtLocalDateTime,
            LocalDateTime updatedAtLocalDateTime) {
        this.roleIdLong = roleIdLong;
        this.bandIdLong = bandIdLong;
        this.roleNameString = roleNameString;
        this.roleDescriptionString = roleDescriptionString;
        this.createdAtLocalDateTime = createdAtLocalDateTime;
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

    public Long getRoleIdLong() {
        return this.roleIdLong;
    }

    public Long getBandIdLong() {
        return this.bandIdLong;
    }

    public String getRoleNameString() {
        return this.roleNameString;
    }

    public String getRoleDescriptionString() {
        return this.roleDescriptionString;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return this.createdAtLocalDateTime;
    }

    public LocalDateTime getUpdatedAtLocalDateTime() {
        return this.updatedAtLocalDateTime;
    }

}
