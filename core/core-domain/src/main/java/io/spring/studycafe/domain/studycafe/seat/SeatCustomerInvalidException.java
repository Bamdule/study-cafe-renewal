package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class SeatCustomerInvalidException extends DomainException {
    public SeatCustomerInvalidException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
