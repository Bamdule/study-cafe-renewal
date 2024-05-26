package io.spring.studycafe.applcation.payment.adapter.card.adapter;

import io.spring.studycafe.domain.payment.PaymentResultType;

public record CardPaymentResponse(
    boolean success,
    String message,
    PaymentResultType resultType,
    String resultCode
) {
}
