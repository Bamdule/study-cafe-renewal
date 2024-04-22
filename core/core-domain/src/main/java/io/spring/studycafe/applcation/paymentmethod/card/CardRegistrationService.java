package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegisterAdapter;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationRequest;
import io.spring.studycafe.applcation.paymentmethod.card.adapter.CardRegistrationResponse;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodDomainService;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodExistCheckCommand;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardRegistrationService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;
    private final CardPaymentMethodDomainService cardPaymentMethodDomainService;
    private final CardRegisterAdapter cardRegisterAdapter;

    public CardRegistrationService(CardPaymentMethodRepository cardPaymentMethodRepository, CardPaymentMethodDomainService cardPaymentMethodDomainService, CardRegisterAdapter cardRegisterAdapter) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
        this.cardPaymentMethodDomainService = cardPaymentMethodDomainService;
        this.cardRegisterAdapter = cardRegisterAdapter;
    }

    @Transactional
    public CardRegistrationResult register(CardRegistrationCommand command) {

        if (cardPaymentMethodDomainService.isExist(new CardPaymentMethodExistCheckCommand(command.memberId()))) {
            throw new RuntimeException("동일한 결제수단이 이미 존재합니다.");
        }

        try {


            CardRegistrationResponse response = cardRegisterAdapter.register(
                new CardRegistrationRequest(
                    command.cardNumber(),
                    command.expirationYear(),
                    command.expirationMonth(),
                    command.cardPassword(),
                    command.personalId()
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
}
