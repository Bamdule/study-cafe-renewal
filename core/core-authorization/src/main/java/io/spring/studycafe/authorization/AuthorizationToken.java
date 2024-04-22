package io.spring.studycafe.authorization;

public record AuthorizationToken(String accessToken, String refreshToken) {
}
