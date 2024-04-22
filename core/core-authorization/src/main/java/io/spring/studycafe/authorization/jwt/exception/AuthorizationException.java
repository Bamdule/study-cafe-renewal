package io.spring.studycafe.authorization.jwt.exception;

public class AuthorizationException extends RuntimeException {
    private final String code;

    public AuthorizationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
