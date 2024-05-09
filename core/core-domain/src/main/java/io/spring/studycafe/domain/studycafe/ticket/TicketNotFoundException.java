package io.spring.studycafe.domain.studycafe.ticket;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class TicketNotFoundException extends DomainException {
    public TicketNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
