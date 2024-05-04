package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.spring.studycafe.domain.common.ExceptionCode.CARD_REGISTER_ADAPTER_NOT_FOUND;

@Component
public class CardRegisterAdapterRouter {

    private final Map<CardPaymentAgency, CardRegisterAdapter> router;

    public CardRegisterAdapterRouter(List<CardRegisterAdapter> cardRegisterAdapters) {

        this.router = cardRegisterAdapters
            .stream()
            .collect(Collectors.toMap(CardRegisterAdapter::getCardPaymentAgency, Function.identity()));
    }

    public CardRegisterAdapter route(CardPaymentAgency cardPaymentAgency) {
        CardRegisterAdapter adapter = router.get(cardPaymentAgency);

        if (adapter == null) {
            throw new CardRegisterAdapterNotFoundException(
                CARD_REGISTER_ADAPTER_NOT_FOUND,
                String.format("%s 카드 등록 업체를 찾을 수 없습니다.", cardPaymentAgency.name())
            );
        }

        return router.get(cardPaymentAgency);
    }
}
