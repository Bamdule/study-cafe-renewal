package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardApiAdapterRouter;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationRequest;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationResponse;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodDomainService;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRegistrationPolicyCheckCommand;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardRegisterService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;
    private final CardPaymentMethodDomainService cardPaymentMethodDomainService;
    private final CardApiAdapterRouter cardApiAdapterRouter;

    public CardRegisterService(CardPaymentMethodRepository cardPaymentMethodRepository, CardPaymentMethodDomainService cardPaymentMethodDomainService, CardApiAdapterRouter cardApiAdapterRouter) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
        this.cardPaymentMethodDomainService = cardPaymentMethodDomainService;
        this.cardApiAdapterRouter = cardApiAdapterRouter;
    }

    @Transactional
    public CardRegisterResult register(CardRegisterCommand command) {

        // 카드 등록 정책 체크
        checkCardRegistrationPolicy(command);

        try {
            CardRegistrationResponse response = cardApiAdapterRouter
                .route(command.cardPaymentAgency())
                .register(
                    new CardRegistrationRequest(
                        command.cardNumber(),
                        command.expirationYear(),
                        command.expirationMonth(),
                        command.cardPassword(),
                        command.personalId()
                    ));

            if (response.success()) {
                cardPaymentMethodRepository.save(new CardPaymentMethod(
                    command.memberId(),
                    response.cardSecretKey(),
                    command.cardNumber().substring(command.cardNumber().length() - 4),
                    command.cardPaymentAgency()
                ));
            }

            //TODO: 카드 등록 히스토리 생성
            return new CardRegisterResult(response.message(), response.success());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void checkCardRegistrationPolicy(CardRegisterCommand command) {
        cardPaymentMethodDomainService.checkRegistrationPolicy(new CardPaymentMethodRegistrationPolicyCheckCommand(command.memberId()));
    }
}
