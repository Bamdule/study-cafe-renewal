package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegisterAdapterRouter;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationRequest;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationResponse;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodDomainService;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRegistrationPolicyCheckCommand;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardRegistrationService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;
    private final CardPaymentMethodDomainService cardPaymentMethodDomainService;
    private final CardRegisterAdapterRouter cardRegisterAdapterRouter;

    public CardRegistrationService(CardPaymentMethodRepository cardPaymentMethodRepository, CardPaymentMethodDomainService cardPaymentMethodDomainService, CardRegisterAdapterRouter cardRegisterAdapterRouter) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
        this.cardPaymentMethodDomainService = cardPaymentMethodDomainService;
        this.cardRegisterAdapterRouter = cardRegisterAdapterRouter;
    }

    @Transactional
    public CardRegistrationResult register(CardRegistrationCommand command) {

        // 카드 등록 정책 체크
        checkCardRegistrationPolicy(command);

        try {
            CardRegistrationResponse response = cardRegisterAdapterRouter
                .route(command.cardPaymentAgency())
                .register(
                    new CardRegistrationRequest(
                        command.cardNumber(),
                        command.expirationYear(),
                        command.expirationMonth(),
                        command.cardPassword(),
                        command.personalId(),
                        command.cardPaymentAgency()
                    ));

            if (response.success()) {
                cardPaymentMethodRepository.save(new CardPaymentMethod(command.memberId(), response.cardSecretKey(), command.cardPaymentAgency()));
            }

            //TODO: 카드 등록 히스토리 생성
            return new CardRegistrationResult(response.message(), response.success());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void checkCardRegistrationPolicy(CardRegistrationCommand command) {
        cardPaymentMethodDomainService.checkRegistrationPolicy(new CardPaymentMethodRegistrationPolicyCheckCommand(command.memberId()));
    }
}
