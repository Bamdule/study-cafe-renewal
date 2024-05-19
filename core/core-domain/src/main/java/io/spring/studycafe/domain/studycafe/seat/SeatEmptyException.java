package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class SeatEmptyException extends DomainException {
    public SeatEmptyException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
