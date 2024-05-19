package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class SeatNotFoundException extends DomainException {
    public SeatNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
