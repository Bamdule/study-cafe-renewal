package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class PaymentServiceNotFoundException extends DomainException {
    public PaymentServiceNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
