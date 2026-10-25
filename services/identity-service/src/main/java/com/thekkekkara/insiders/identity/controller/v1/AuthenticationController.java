
package com.thekkekkara.insiders.identity.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekkekkara.insiders.identity.dto.auth.LoginRequest;
import com.thekkekkara.insiders.identity.dto.auth.LoginResponse;
import com.thekkekkara.insiders.identity.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(
            AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = this.authenticationService.login(loginRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loginResponse);
    }
}
