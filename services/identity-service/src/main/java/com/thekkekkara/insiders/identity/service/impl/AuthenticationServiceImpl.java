
package com.thekkekkara.insiders.identity.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.thekkekkara.insiders.identity.domain.User;
import com.thekkekkara.insiders.identity.dto.auth.LoginRequest;
import com.thekkekkara.insiders.identity.dto.auth.LoginResponse;
import com.thekkekkara.insiders.identity.service.AuthenticationService;
import com.thekkekkara.insiders.identity.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final long ACCESS_TOKEN_EXPIRY_SECONDS = 900;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final String jwtIssuerString;

    public AuthenticationServiceImpl(
            UserService userService,
            PasswordEncoder passwordEncoder,
            JwtEncoder jwtEncoder,
            @Value("${JWT_ISSUER}") String jwtIssuerString) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.jwtIssuerString = jwtIssuerString;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user;

        try {
            user = this.userService.getUserByUsernameForAuth(
                    loginRequest.usernameString());
        } catch (RuntimeException exception) {
            throw new BadCredentialsException(
                    "Invalid username or password");
        }

        if (!user.getEnabledBoolean()
                || user.getAccountLockedBoolean()
                || user.getPasswordHashString() == null
                || !this.passwordEncoder.matches(
                        loginRequest.passwordString(),
                        user.getPasswordHashString())) {

            throw new BadCredentialsException(
                    "Invalid username or password");
        }

        // Retrieve the user's platform-admin status from the database.
        boolean platformAdminBoolean = this.userService.isPlatformAdminByUserId(
                user.getUserIdLong());

        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plusSeconds(
                ACCESS_TOKEN_EXPIRY_SECONDS);

        // Create the claims included in the signed JWT.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(this.jwtIssuerString)
                .subject(user.getUserIdLong().toString())
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .claim("username", user.getUsernameString())
                .claim("platform_admin", platformAdminBoolean)
                .build();

        String accessToken = this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        // Return the token and the admin status.
        return new LoginResponse(
                accessToken,
                "Bearer",
                ACCESS_TOKEN_EXPIRY_SECONDS,
                platformAdminBoolean);
    }
}
