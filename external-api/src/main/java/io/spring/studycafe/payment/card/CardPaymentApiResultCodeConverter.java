package io.spring.studycafe.payment.card;

import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentApiResultType;

public interface CardPaymentApiResultCodeConverter {
    CardPaymentApiResultType convert(String resultCode);
}
