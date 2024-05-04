package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.spring.studycafe.domain.common.ExceptionCode.CARD_REGISTER_ADAPTER_NOT_FOUND;

@Component
public class CardApiAdapterRouter {

    private final Map<CardPaymentAgency, CardApiAdapter> router;

    public CardApiAdapterRouter(List<CardApiAdapter> cardApiAdapters) {

        this.router = cardApiAdapters
            .stream()
            .collect(Collectors.toMap(CardApiAdapter::getCardPaymentAgency, Function.identity()));
    }

    public CardApiAdapter route(CardPaymentAgency cardPaymentAgency) {
        CardApiAdapter adapter = router.get(cardPaymentAgency);

        if (adapter == null) {
            throw new CardApiAdapterNotFoundException(
                CARD_REGISTER_ADAPTER_NOT_FOUND,
                String.format("%s 카드 등록 업체를 찾을 수 없습니다.", cardPaymentAgency.name())
            );
        }

        return router.get(cardPaymentAgency);
    }
}
