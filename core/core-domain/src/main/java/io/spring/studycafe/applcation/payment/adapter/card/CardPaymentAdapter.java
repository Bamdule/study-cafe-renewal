package io.spring.studycafe.applcation.payment.adapter.card;

import io.spring.studycafe.applcation.payment.adapter.PaymentResult;
import io.spring.studycafe.applcation.payment.adapter.PaymentAdapter;
import io.spring.studycafe.applcation.payment.adapter.card.adapter.CardPaymentApiAdapterNotFoundException;
import io.spring.studycafe.applcation.payment.adapter.card.adapter.CardPaymentApiRouter;
import io.spring.studycafe.applcation.payment.adapter.card.adapter.CardPaymentRequest;
import io.spring.studycafe.applcation.payment.adapter.card.adapter.CardPaymentResponse;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import io.spring.studycafe.domain.paymentmethod.card.exception.CardPaymentMethodNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentAdapter implements PaymentAdapter {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;
    private final CardPaymentApiRouter cardPaymentApiRouter;

    public CardPaymentAdapter(CardPaymentMethodRepository cardPaymentMethodRepository, CardPaymentApiRouter cardPaymentApiRouter) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
        this.cardPaymentApiRouter = cardPaymentApiRouter;
    }

    @Override
    public PaymentResult purchase(Order order) {
        CardPaymentMethod card = cardPaymentMethodRepository.findById(order.getPaymentMethodId())
            .orElseThrow(() -> new CardPaymentMethodNotFoundException(ExceptionCode.CARD_PAYMENT_METHOD_NOT_FOUND));

        if (card.getMemberId() != order.getMemberId()) {
            throw new CardPaymentMethodNotFoundException(ExceptionCode.CARD_PAYMENT_METHOD_NOT_FOUND);
        }

        CardPaymentRequest cardPaymentRequest = new CardPaymentRequest(order.getOrderCode(), order.getName(), order.getTotalPrice(), card.getCardSecretKey());

        CardPaymentResponse cardPaymentResponse =
            cardPaymentApiRouter.route(card.getCardPaymentAgency())
                .orElseThrow(() -> new CardPaymentApiAdapterNotFoundException(ExceptionCode.CARD_PAYMENT_API_ADAPTER_NOT_FOUND))
                .purchase(cardPaymentRequest);

        //결제 정보 저장

        PaymentResult result = new PaymentResult(
            1L,
            order.getName(),
            order.getTotalPrice(),
            cardPaymentResponse.message(),
            cardPaymentResponse.resultCode(),
            cardPaymentResponse.resultType(),
            cardPaymentResponse.success()
        );

        return result;
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.CARD;
    }
}
