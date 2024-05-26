package io.spring.studycafe.applcation.payment.adapter.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CardPaymentApiRouter {
    private final Map<CardPaymentAgency, CardPaymentApiAdapter> adapterMap;

    public CardPaymentApiRouter(List<CardPaymentApiAdapter> adapters) {
        this.adapterMap = adapters
            .stream()
            .collect(Collectors.toMap(CardPaymentApiAdapter::getCardPaymentAgency, Function.identity()));
    }

    public Optional<CardPaymentApiAdapter> route(CardPaymentAgency cardPaymentAgency) {
        return Optional.ofNullable(adapterMap.get(cardPaymentAgency));
    }
}
