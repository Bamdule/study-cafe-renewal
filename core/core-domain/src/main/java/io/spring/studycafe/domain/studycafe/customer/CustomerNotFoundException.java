package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CustomerNotFoundException extends DomainException {

    public CustomerNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
