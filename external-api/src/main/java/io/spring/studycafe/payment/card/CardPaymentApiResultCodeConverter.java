package io.spring.studycafe.payment.card;

import io.spring.studycafe.domain.payment.PaymentResultType;

public interface CardPaymentApiResultCodeConverter {
    PaymentResultType convert(String resultCode);
}
