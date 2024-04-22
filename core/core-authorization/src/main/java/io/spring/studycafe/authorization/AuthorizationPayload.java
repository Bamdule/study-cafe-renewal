package io.spring.studycafe.authorization;

public record AuthorizationPayload(Long id, String email, String name) {
}
