package com.thekkekkara.insiders.identity.service;

import com.thekkekkara.insiders.identity.domain.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    User getUserByUsername(String usernameString);

    User getUserByEmail(String emailString);

    User getUserByUsernameForAuth(String usernameString);

    boolean doesUserExistByUsername(String usernameString);

    boolean doesUserExistByEmail(String emailString);

    void deleteUserById(Long userIdLong);

    boolean isPlatformAdminByUserId(Long userIdLong);
}
