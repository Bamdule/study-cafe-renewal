package io.spring.studycafe.payment.card.nicepay;

import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentApiResultType;
import io.spring.studycafe.payment.card.CardPaymentApiResultCodeConverter;
import org.springframework.stereotype.Component;

@Component
public class NicepayCardPaymentApiResultCodeConverter implements CardPaymentApiResultCodeConverter {
    @Override
    public CardPaymentApiResultType convert(String resultCode) {
        switch (resultCode) {
            case "0000":
                return CardPaymentApiResultType.PAYMENT_SUCCESS;
            case "0001":
                return CardPaymentApiResultType.LACK_OF_MONEY;
            case "0002":
                return CardPaymentApiResultType.EXPIRED_CARD;
            case "0003":
                return CardPaymentApiResultType.DELETED_CARD;
            default:
                return CardPaymentApiResultType.PAYMENT_FAILURE;
        }
    }
}
