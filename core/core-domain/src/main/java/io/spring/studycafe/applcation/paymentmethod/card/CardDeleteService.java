package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardApiAdapterRouter;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardDeleteRequest;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardDeleteResponse;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import io.spring.studycafe.domain.paymentmethod.card.exception.CardPaymentMethodNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardDeleteService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;
    private final CardApiAdapterRouter cardApiAdapterRouter;

    public CardDeleteService(CardPaymentMethodRepository cardPaymentMethodRepository, CardApiAdapterRouter cardApiAdapterRouter) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
        this.cardApiAdapterRouter = cardApiAdapterRouter;
    }

    @Transactional
    public CardDeleteResult delete(CardDeleteCommand command) {


        CardPaymentMethod paymentMethod = cardPaymentMethodRepository.findById(command.cardPaymentMethodId())
            .orElseThrow(() -> new CardPaymentMethodNotFoundException(ExceptionCode.CARD_PAYMENT_METHOD_NOT_FOUND));

        cardPaymentMethodRepository.delete(paymentMethod);

        CardDeleteResponse response = cardApiAdapterRouter.route(paymentMethod.getCardPaymentAgency())
            .delete(new CardDeleteRequest(paymentMethod.getCardSecretKey()));

        return new CardDeleteResult(response.message(), response.success());

    }

}
