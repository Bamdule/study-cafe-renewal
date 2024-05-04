package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;

public interface CardApiAdapter {
    CardRegistrationResponse register(CardRegistrationRequest request);

    CardDeleteResponse delete(CardDeleteRequest request);

    CardPaymentAgency getCardPaymentAgency();
}
