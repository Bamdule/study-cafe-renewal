package io.spring.studycafe.paymentmethod.card.nicepay;

import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegisterAdapter;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationRequest;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationResponse;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FakeNicepayCardRegister implements CardRegisterAdapter {

    private final NicePayProperties nicePayProperties;

    public FakeNicepayCardRegister(NicePayProperties nicePayProperties) {
        log.info("domain : {}", nicePayProperties.getDomain());
        log.info("basicAuthorization : {}", nicePayProperties.getBasicAuthorization());
        log.info("clientKey : {}", nicePayProperties.getClientKey());
        log.info("secretKey : {}", nicePayProperties.getSecretKey());
        this.nicePayProperties = nicePayProperties;
    }

    @Override
    public CardRegistrationResponse register(CardRegistrationRequest request) {


        return new CardRegistrationResponse(UUID.randomUUID().toString(), "카드 등록 성공", true);
    }

    @Override
    public CardPaymentAgency getCardPaymentAgency() {
        return CardPaymentAgency.NICEPAY;
    }
}
