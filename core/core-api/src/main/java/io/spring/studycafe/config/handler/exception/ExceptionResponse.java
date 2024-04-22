package io.spring.studycafe.config.handler.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private final LocalDateTime timestamp;
    private final String message;
    private final String code;

    public ExceptionResponse(String message, String code) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
