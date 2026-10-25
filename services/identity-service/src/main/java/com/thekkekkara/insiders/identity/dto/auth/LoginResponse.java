
package com.thekkekkara.insiders.identity.dto.auth;

public record LoginResponse(
        String accessTokenString,
        String tokenTypeString,
        long expiresInSecondsLong,
        boolean platformAdminBoolean) {
}
