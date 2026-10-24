package com.thekkekkara.insiders.identity.repository.mapper;

import org.springframework.stereotype.Component;

import com.thekkekkara.insiders.identity.domain.User;
import com.thekkekkara.insiders.identity.dto.user.CreateUserRequest;
import com.thekkekkara.insiders.identity.dto.user.UserResponse;
import com.thekkekkara.insiders.identity.entity.UserEntity;

@Component
public class UserRowMapper {

    public User toDomain(CreateUserRequest request) {
        return new User(
                null,
                request.usernameString(),
                request.emailString(),
                request.passwordString(),
                request.firstNameString(),
                request.lastNameString(),
                true,
                false,
                null,
                null);
    }

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getUserIdLong(),
                entity.getUsernameString(),
                entity.getEmailString(),
                entity.getPasswordHashString(),
                entity.getFirstNameString(),
                entity.getLastNameString(),
                entity.getEnabledBoolean(),
                entity.getAccountLockedBoolean(),
                entity.getCreatedAtLocalDateTime(),
                entity.getUpdatedAtLocalDateTime());
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getUserIdLong(),
                user.getUsernameString(),
                user.getEmailString(),
                user.getPasswordHashString(),
                user.getFirstNameString(),
                user.getLastNameString(),
                user.getEnabledBoolean(),
                user.getAccountLockedBoolean(),
                user.getCreatedAtLocalDateTime(),
                user.getUpdatedAtLocalDateTime());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUserIdLong(),
                user.getUsernameString(),
                user.getEmailString(),
                user.getFirstNameString(),
                user.getLastNameString(),
                user.getEnabledBoolean(),
                user.getAccountLockedBoolean(),
                user.getCreatedAtLocalDateTime(),
                user.getUpdatedAtLocalDateTime());
    }
}
