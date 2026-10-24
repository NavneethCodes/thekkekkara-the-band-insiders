
package com.thekkekkara.insiders.identity.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thekkekkara.insiders.identity.domain.User;
import com.thekkekkara.insiders.identity.entity.UserEntity;
import com.thekkekkara.insiders.identity.repository.UserRepository;
import com.thekkekkara.insiders.identity.repository.mapper.UserRowMapper;
import com.thekkekkara.insiders.identity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserRowMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository repository,
            UserRowMapper mapper,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        if (doesUserExistByUsername(user.getUsernameString())) {
            throw new IllegalArgumentException("Username already taken!");
        }

        if (doesUserExistByEmail(user.getEmailString())) {
            throw new IllegalArgumentException("Email already taken!");
        }

        String encodedPassword = passwordEncoder.encode(
                user.getPasswordHashString());

        User userWithEncodedPassword = new User(
                null,
                user.getUsernameString(),
                user.getEmailString(),
                encodedPassword,
                user.getFirstNameString(),
                user.getLastNameString(),
                true,
                false,
                null,
                null);

        UserEntity entity = this.mapper.toEntity(userWithEncodedPassword);
        UserEntity savedEntity = this.repository.save(entity);

        return this.mapper.toDomain(savedEntity);
    }

    @Override
    public User getUserById(Long userIdLong) {
        return this.repository.findById(userIdLong)
                .map(this.mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with ID: " + userIdLong));
    }

    @Override
    public User getUserByUsername(String usernameString) {
        return this.repository.findByUsername(usernameString)
                .map(this.mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with username: " + usernameString));
    }

    @Override
    public User getUserByEmail(String emailString) {
        return this.repository.findByEmail(emailString)
                .map(this.mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with email: " + emailString));
    }

    @Override
    public User getUserByUsernameForAuth(String usernameString) {
        return this.repository.findForAuthenticationByUsername(usernameString)
                .map(this.mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with username: " + usernameString));
    }

    @Override
    public boolean doesUserExistByUsername(String usernameString) {
        return this.repository.existsByUsername(usernameString);
    }

    @Override
    public boolean doesUserExistByEmail(String emailString) {
        return this.repository.existsByEmail(emailString);
    }

    @Override
    public void deleteUserById(Long userIdLong) {
        if (!this.repository.existsByUserId(userIdLong)) {
            throw new IllegalArgumentException(
                    "User not found with ID: " + userIdLong);
        }

        this.repository.deleteById(userIdLong);
    }
}
