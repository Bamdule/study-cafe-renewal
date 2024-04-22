package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service
public class CardSearchService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;

    public CardSearchService(CardPaymentMethodRepository cardPaymentMethodRepository) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
    }

    public CardPaymentMethod search(Long memberId) {
        return cardPaymentMethodRepository.findByMemberId(memberId)
            .orElseThrow(RuntimeException::new);
    }
}
