package com.thekkekkara.insiders.identity.entity;

import java.time.LocalDateTime;

public class RoleEntity {

    private Long roleIdLong;
    private Long bandIdLong;
    private String roleNameString;
    private String roleDescriptionString;
    private LocalDateTime createdAtLocalDateTime;
    private LocalDateTime updatedAtLocalDateTime;

    public RoleEntity() {
    }

    public RoleEntity(
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
        return roleIdLong;
    }

    public void setRoleIdLong(Long roleIdLong) {
        this.roleIdLong = roleIdLong;
    }

    public Long getBandIdLong() {
        return bandIdLong;
    }

    public void setBandIdLong(Long bandIdLong) {
        this.bandIdLong = bandIdLong;
    }

    public String getRoleNameString() {
        return roleNameString;
    }

    public void setRoleNameString(String roleNameString) {
        this.roleNameString = roleNameString;
    }

    public String getRoleDescriptionString() {
        return roleDescriptionString;
    }

    public void setRoleDescriptionString(String roleDescriptionString) {
        this.roleDescriptionString = roleDescriptionString;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return createdAtLocalDateTime;
    }

    public void setCreatedAtLocalDateTime(LocalDateTime createdAtLocalDateTime) {
        this.createdAtLocalDateTime = createdAtLocalDateTime;
    }

    public LocalDateTime getUpdatedAtLocalDateTime() {
        return updatedAtLocalDateTime;
    }

    public void setUpdatedAtLocalDateTime(LocalDateTime updatedAtLocalDateTime) {
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

}
