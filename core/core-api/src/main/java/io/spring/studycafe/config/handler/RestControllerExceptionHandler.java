package io.spring.studycafe.config.handler;

import io.spring.studycafe.config.handler.exception.ExceptionResponse;
import io.spring.studycafe.config.handler.exception.TokenAuthorizationException;
import io.spring.studycafe.domain.common.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ExceptionResponse> handleDomainException(DomainException exception) {
        ExceptionResponse response = new ExceptionResponse(
            exception.getMessage(),
            exception.getCode()
        );

        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @ExceptionHandler(TokenAuthorizationException.class)
    public ResponseEntity<ExceptionResponse> handleTokenAuthorizationException(TokenAuthorizationException exception) {
        ExceptionResponse response = new ExceptionResponse(
            exception.getMessage(),
            exception.getCode()
        );

        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage(), "MISSING_PARAMETER"));
    }


}
