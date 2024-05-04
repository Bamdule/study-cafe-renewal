package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CardApiAdapterNotFoundException extends DomainException {

    public CardApiAdapterNotFoundException(String message, String code, int httpStatus) {
        super(message, code, httpStatus);
    }

    public CardApiAdapterNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CardApiAdapterNotFoundException(ExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }
}
