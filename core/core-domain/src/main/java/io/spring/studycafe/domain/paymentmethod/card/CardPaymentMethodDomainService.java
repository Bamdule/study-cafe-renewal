package io.spring.studycafe.domain.paymentmethod.card;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.paymentmethod.card.exception.CardRegistrationUnavailableException;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentMethodDomainService {

    private final int AVAILABLE_NUMBER_OF_CARDS = 3;

    private final CardPaymentMethodRepository cardPaymentMethodRepository;

    public CardPaymentMethodDomainService(CardPaymentMethodRepository cardPaymentMethodRepository) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
    }


    public void checkRegistrationPolicy(CardPaymentMethodRegistrationPolicyCheckCommand command) {
        if (isCardRegistrationCountExceeded(command)) {
            throw new CardRegistrationUnavailableException(
                ExceptionCode.CARD_REGISTRATION_COUNT_EXCEEDED,
                String.format("카드는 최대 %d개 까지 등록할 수 있습니다.", AVAILABLE_NUMBER_OF_CARDS)
            );
        }
    }

    private boolean isCardRegistrationCountExceeded(CardPaymentMethodRegistrationPolicyCheckCommand command) {
        return cardPaymentMethodRepository.findAllByMemberId(command.memberId()).size() >= AVAILABLE_NUMBER_OF_CARDS;
    }
}
