package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;

public interface CardRegisterAdapter {
    CardRegistrationResponse register(CardRegistrationRequest request);

    CardPaymentAgency getCardPaymentAgency();
}
