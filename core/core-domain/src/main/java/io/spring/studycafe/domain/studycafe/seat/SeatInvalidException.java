package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class SeatInvalidException extends DomainException {
    public SeatInvalidException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
