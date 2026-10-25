
package com.thekkekkara.insiders.identity.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thekkekkara.insiders.identity.domain.User;
import com.thekkekkara.insiders.identity.dto.user.CreateUserRequest;
import com.thekkekkara.insiders.identity.dto.user.UserResponse;
import com.thekkekkara.insiders.identity.repository.mapper.UserRowMapper;
import com.thekkekkara.insiders.identity.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRowMapper userRowMapper;

    public UserController(
            UserService userService,
            UserRowMapper userRowMapper) {
        this.userService = userService;
        this.userRowMapper = userRowMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        User user = this.userRowMapper.toDomain(request);
        User createdUser = this.userService.createUser(user);
        UserResponse response = this.userRowMapper.toResponse(createdUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/userId")
    public ResponseEntity<UserResponse> getUserById(
            @RequestParam Long userId) {

        User user = this.userService.getUserById(userId);
        UserResponse response = this.userRowMapper.toResponse(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/username")
    public ResponseEntity<UserResponse> getUserByUsername(
            @RequestParam String usernameString) {

        User user = this.userService.getUserByUsername(usernameString);
        UserResponse response = this.userRowMapper.toResponse(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/useremail")
    public ResponseEntity<UserResponse> getUserByEmail(
            @RequestParam String emailString) {

        User user = this.userService.getUserByEmail(emailString);
        UserResponse response = this.userRowMapper.toResponse(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable Long userId) {

        this.userService.deleteUserById(userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
