package com.thekkekkara.insiders.identity.entity;

import java.time.LocalDateTime;

public class UserEntity {

    private Long userIdLong;
    private String usernameString;
    private String emailString;
    private String passwordHashString;
    private String firstNameString;
    private String lastNameString;
    private Boolean enabledBoolean;
    private Boolean accountLockedBoolean;
    private LocalDateTime createdAtLocalDateTime;
    private LocalDateTime updatedAtLocalDateTime;

    public UserEntity() {
    }

    public UserEntity(
            Long userIdLong,
            String usernameString,
            String emailString,
            String passwordHashString,
            String fullNameString,
            String lastNameString,
            Boolean enabledBoolean,
            Boolean accountLockedBoolean,
            LocalDateTime createdAtLocalDateTime,
            LocalDateTime updatedAtLocalDateTime) {
        this.userIdLong = userIdLong;
        this.usernameString = usernameString;
        this.emailString = emailString;
        this.passwordHashString = passwordHashString;
        this.firstNameString = fullNameString;
        this.lastNameString = lastNameString;
        this.enabledBoolean = enabledBoolean;
        this.accountLockedBoolean = accountLockedBoolean;
        this.createdAtLocalDateTime = createdAtLocalDateTime;
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

    public Long getUserIdLong() {
        return this.userIdLong;
    }

    public void setUserIdLong(Long userIdLong) {
        this.userIdLong = userIdLong;
    }

    public String getUsernameString() {
        return this.usernameString;
    }

    public void setUsernameString(String usernameString) {
        this.usernameString = usernameString;
    }

    public String getEmailString() {
        return this.emailString;
    }

    public void setEmailString(String emailString) {
        this.emailString = emailString;
    }

    public String getPasswordHashString() {
        return this.passwordHashString;
    }

    public void setPasswordHashString(String passwordHashString) {
        this.passwordHashString = passwordHashString;
    }

    public String getFirstNameString() {
        return this.firstNameString;
    }

    public void setFirstNameString(String fullNameString) {
        this.firstNameString = fullNameString;
    }

    public String getLastNameString() {
        return this.lastNameString;
    }

    public void setLastNameString(String lastNameString) {
        this.lastNameString = lastNameString;
    }

    public Boolean getEnabledBoolean() {
        return this.enabledBoolean;
    }

    public void setEnabledBoolean(Boolean enabledBoolean) {
        this.enabledBoolean = enabledBoolean;
    }

    public Boolean getAccountLockedBoolean() {
        return this.accountLockedBoolean;
    }

    public void setAccountLockedBoolean(Boolean accountLockedBoolean) {
        this.accountLockedBoolean = accountLockedBoolean;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return this.createdAtLocalDateTime;
    }

    public void setCreatedAtLocalDateTime(LocalDateTime createdAtLocalDateTime) {
        this.createdAtLocalDateTime = createdAtLocalDateTime;
    }

    public LocalDateTime getUpdatedAtLocalDateTime() {
        return this.updatedAtLocalDateTime;
    }

    public void setUpdatedAtLocalDateTime(LocalDateTime updatedAtLocalDateTime) {
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

}
