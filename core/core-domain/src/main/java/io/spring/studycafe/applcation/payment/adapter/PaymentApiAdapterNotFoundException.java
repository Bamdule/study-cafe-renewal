package io.spring.studycafe.applcation.payment.adapter;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class PaymentApiAdapterNotFoundException extends DomainException {
    public PaymentApiAdapterNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
