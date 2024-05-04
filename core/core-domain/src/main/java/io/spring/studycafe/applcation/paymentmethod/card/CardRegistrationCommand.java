package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;

public record CardRegistrationCommand(
    Long memberId,
    String cardNumber,
    String expirationYear,
    String expirationMonth,
    String cardPassword,
    String personalId,
    CardPaymentAgency cardPaymentAgency
) {
}
