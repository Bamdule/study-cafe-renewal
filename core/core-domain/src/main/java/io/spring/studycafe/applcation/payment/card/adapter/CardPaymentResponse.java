package io.spring.studycafe.applcation.payment.card.adapter;

public record CardPaymentResponse(
    boolean success,
    String message,
    CardPaymentApiResultType resultType,
    String resultCode
) {
}
