package io.spring.studycafe.applcation.payment.adapter;

import io.spring.studycafe.domain.payment.PaymentResultType;

public record PaymentResult(
    Long paymentId,
    String itemName,
    Long itemPrice,
    String message,
    String resultCode,
    PaymentResultType resultType,
    boolean success
) {
}
