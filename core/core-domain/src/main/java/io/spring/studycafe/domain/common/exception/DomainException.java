package io.spring.studycafe.domain.common.exception;

import io.spring.studycafe.domain.common.ExceptionCode;
import lombok.Getter;

public class DomainException extends BaseException {

    public DomainException(String message, String code, int httpStatus) {
        super(message, code, httpStatus);
    }

    public DomainException(ExceptionCode exceptionCode) {
        this(exceptionCode.getMessage(), exceptionCode.name(), exceptionCode.getHttpStatus().value());
    }
}
