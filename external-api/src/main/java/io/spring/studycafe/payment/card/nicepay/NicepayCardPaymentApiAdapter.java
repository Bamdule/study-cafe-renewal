package io.spring.studycafe.payment.card.nicepay;

import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentApiAdapter;
import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentRequest;
import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentResponse;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import io.spring.studycafe.paymentmethod.card.nicepay.NicePayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NicepayCardPaymentApiAdapter implements CardPaymentApiAdapter {

    private final NicePayProperties nicePayProperties;
    private final NicepayCardPaymentApiResultCodeConverter nicepayCardPaymentApiResultCodeConverter;

    public NicepayCardPaymentApiAdapter(NicePayProperties nicePayProperties, NicepayCardPaymentApiResultCodeConverter nicepayCardPaymentApiResultCodeConverter) {
        this.nicepayCardPaymentApiResultCodeConverter = nicepayCardPaymentApiResultCodeConverter;
        log.info("NicepayCardPaymentApiAdapter - domain : {}", nicePayProperties.getDomain());
        this.nicePayProperties = nicePayProperties;
    }

    @Override
    public CardPaymentResponse purchase(CardPaymentRequest request) {
        return new CardPaymentResponse(
            true,
            "결제 성공",
            nicepayCardPaymentApiResultCodeConverter.convert("0000"),
            "0000"
        );
    }

    @Override
    public CardPaymentAgency getCardPaymentAgency() {
        return CardPaymentAgency.NICEPAY;
    }
}
