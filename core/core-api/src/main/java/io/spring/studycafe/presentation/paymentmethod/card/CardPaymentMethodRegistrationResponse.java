package io.spring.studycafe.presentation.paymentmethod.card;

public record CardPaymentMethodRegistrationResponse(
    String message,
    boolean success
) {
}
