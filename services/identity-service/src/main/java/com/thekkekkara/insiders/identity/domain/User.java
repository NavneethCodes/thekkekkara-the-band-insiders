package com.thekkekkara.insiders.identity.domain;

import java.time.LocalDateTime;

public class User {

    private final Long userIdLong;
    private final String usernameString;
    private final String emailString;
    private final String passwordHashString;
    private final String firstNameString;
    private final String lastNameString;
    private final Boolean enabledBoolean;
    private final Boolean accountLockedBoolean;
    private final LocalDateTime createdAtLocalDateTime;
    private final LocalDateTime updatedAtLocalDateTime;

    public User(Long user_id, String username, String email, String password_hash, String first_name, String last_name,
            Boolean enabled, Boolean account_locked, LocalDateTime createdAtLocalDateTime,
            LocalDateTime updatedAtLocalDateTime) {
        this.userIdLong = user_id;
        this.usernameString = username;
        this.emailString = email;
        this.passwordHashString = password_hash;
        this.firstNameString = first_name;
        this.lastNameString = last_name;
        this.enabledBoolean = enabled;
        this.accountLockedBoolean = account_locked;
        this.createdAtLocalDateTime = createdAtLocalDateTime;
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

    public Long getUserIdLong() {
        return userIdLong;
    }

    public String getUsernameString() {
        return usernameString;
    }

    public String getEmailString() {
        return emailString;
    }

    public String getPasswordHashString() {
        return passwordHashString;
    }

    public String getFirstNameString() {
        return firstNameString;
    }

    public String getLastNameString() {
        return lastNameString;
    }

    public Boolean getEnabledBoolean() {
        return enabledBoolean;
    }

    public Boolean getAccountLockedBoolean() {
        return accountLockedBoolean;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return createdAtLocalDateTime;
    }

    public LocalDateTime getUpdatedAtLocalDateTime() {
        return updatedAtLocalDateTime;
    }

}
