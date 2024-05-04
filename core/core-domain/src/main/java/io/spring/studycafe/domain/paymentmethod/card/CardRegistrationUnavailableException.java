package io.spring.studycafe.domain.paymentmethod.card;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class CardRegistrationUnavailableException extends DomainException {
    public CardRegistrationUnavailableException(String message, String code, int httpStatus) {
        super(message, code, httpStatus);
    }

    public CardRegistrationUnavailableException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CardRegistrationUnavailableException(ExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }
}
