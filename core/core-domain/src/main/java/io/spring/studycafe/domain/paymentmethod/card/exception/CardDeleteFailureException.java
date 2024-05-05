package io.spring.studycafe.domain.paymentmethod.card.exception;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CardDeleteFailureException extends DomainException {
    public CardDeleteFailureException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
