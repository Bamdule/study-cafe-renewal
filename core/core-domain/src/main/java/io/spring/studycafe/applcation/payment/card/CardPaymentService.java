package io.spring.studycafe.applcation.payment.card;

import io.spring.studycafe.applcation.payment.PaymentResult;
import io.spring.studycafe.applcation.payment.PaymentService;
import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentApiAdapterNotFoundException;
import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentApiRouter;
import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentRequest;
import io.spring.studycafe.applcation.payment.card.adapter.CardPaymentResponse;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import io.spring.studycafe.domain.paymentmethod.card.exception.CardPaymentMethodNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentService implements PaymentService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;
    private final CardPaymentApiRouter cardPaymentApiRouter;

    public CardPaymentService(CardPaymentMethodRepository cardPaymentMethodRepository, CardPaymentApiRouter cardPaymentApiRouter) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
        this.cardPaymentApiRouter = cardPaymentApiRouter;
    }

    @Override
    public PaymentResult purchase(Order order) {
        CardPaymentMethod card = cardPaymentMethodRepository.findById(order.getPaymentMethodId())
            .orElseThrow(() -> new CardPaymentMethodNotFoundException(ExceptionCode.CARD_PAYMENT_METHOD_NOT_FOUND));

        CardPaymentRequest cardPaymentRequest = new CardPaymentRequest(order.getOrderCode(), order.getItemName(), order.getItemPrice(), card.getCardSecretKey());

        CardPaymentResponse cardPaymentResponse =
            cardPaymentApiRouter.route(card.getCardPaymentAgency())
                .orElseThrow(() -> new CardPaymentApiAdapterNotFoundException(ExceptionCode.CARD_PAYMENT_API_ADAPTER_NOT_FOUND))
                .purchase(cardPaymentRequest);

        //결제 정보 저장

        PaymentResult result = new PaymentResult(
            1L,
            order.getItemName(),
            order.getItemPrice(),
            cardPaymentResponse.message(),
            cardPaymentResponse.resultType().name(),
            cardPaymentResponse.success()
        );

        return result;
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.CARD;
    }
}
