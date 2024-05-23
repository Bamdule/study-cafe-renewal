package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class SeatOnlyOneUsableException extends DomainException {
    public SeatOnlyOneUsableException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
