package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CustomerAlreadyRegisteredException extends DomainException {
    public CustomerAlreadyRegisteredException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
