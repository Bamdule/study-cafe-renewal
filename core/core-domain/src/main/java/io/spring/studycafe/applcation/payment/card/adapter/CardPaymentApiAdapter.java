package io.spring.studycafe.applcation.payment.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;

public interface CardPaymentApiAdapter {
    CardPaymentResponse purchase(CardPaymentRequest request);

    CardPaymentAgency getCardPaymentAgency();
}
