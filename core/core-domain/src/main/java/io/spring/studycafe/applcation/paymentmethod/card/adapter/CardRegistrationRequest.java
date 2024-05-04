package io.spring.studycafe.applcation.paymentmethod.card.adapter;

public record CardRegistrationRequest(
    String cardNumber,
    String expirationYear,
    String expirationMonth,
    String cardPassword,
    String personalId
) {
}
