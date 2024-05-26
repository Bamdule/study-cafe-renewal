package io.spring.studycafe.payment.card.nicepay;

import io.spring.studycafe.domain.payment.PaymentResultType;
import io.spring.studycafe.payment.card.CardPaymentApiResultCodeConverter;
import org.springframework.stereotype.Component;

@Component
public class NicepayCardPaymentApiResultCodeConverter implements CardPaymentApiResultCodeConverter {
    @Override
    public PaymentResultType convert(String resultCode) {
        switch (resultCode) {
            case "0000":
                return PaymentResultType.PAYMENT_SUCCESS;
            case "0001":
                return PaymentResultType.LACK_OF_MONEY;
            case "0002":
                return PaymentResultType.EXPIRED;
            case "0003":
                return PaymentResultType.DELETED;
            default:
                return PaymentResultType.PAYMENT_FAILURE;
        }
    }
}
