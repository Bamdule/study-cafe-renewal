package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CardRegisterAdapterNotFoundException extends DomainException {

    public CardRegisterAdapterNotFoundException(String message, String code, int httpStatus) {
        super(message, code, httpStatus);
    }

    public CardRegisterAdapterNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CardRegisterAdapterNotFoundException(ExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }
}
