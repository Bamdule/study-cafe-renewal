package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class SeatAlreadyInUseException extends DomainException {
    public SeatAlreadyInUseException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
