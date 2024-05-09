package io.spring.studycafe.applcation.payment.card.adapter;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CardPaymentApiAdapterNotFoundException extends DomainException {
    public CardPaymentApiAdapterNotFoundException(String message, String code, int httpStatus) {
        super(message, code, httpStatus);
    }

    public CardPaymentApiAdapterNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CardPaymentApiAdapterNotFoundException(ExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }
}
