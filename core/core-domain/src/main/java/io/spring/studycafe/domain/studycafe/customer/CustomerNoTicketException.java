package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CustomerNoTicketException extends DomainException {

    public CustomerNoTicketException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
