package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;

public record CardRegistrationRequest(
    String cardNumber,
    String expirationYear,
    String expirationMonth,
    String cardPassword,
    String personalId,
    CardPaymentAgency cardPaymentAgency
) {
}
