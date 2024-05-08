package io.spring.studycafe.domain.studycafe;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class StudyCafeNotFoundException extends DomainException {
    public StudyCafeNotFoundException(ExceptionCode code) {
        super(code);
    }
}
