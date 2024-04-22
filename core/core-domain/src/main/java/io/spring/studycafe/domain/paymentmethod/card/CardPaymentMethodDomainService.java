package io.spring.studycafe.domain.paymentmethod.card;

import org.springframework.stereotype.Service;

@Service
public class CardPaymentMethodDomainService {

    private final CardPaymentMethodRepository cardPaymentMethodRepository;

    public CardPaymentMethodDomainService(CardPaymentMethodRepository cardPaymentMethodRepository) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
    }


    public boolean isExist(CardPaymentMethodExistCheckCommand command) {
        return cardPaymentMethodRepository.findByMemberId(command.memberId()).isPresent();
    }
}
