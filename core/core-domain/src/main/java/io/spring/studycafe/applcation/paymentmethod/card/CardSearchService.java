package io.spring.studycafe.applcation.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardSearchService {
    private final CardPaymentMethodRepository cardPaymentMethodRepository;

    public CardSearchService(CardPaymentMethodRepository cardPaymentMethodRepository) {
        this.cardPaymentMethodRepository = cardPaymentMethodRepository;
    }

    public List<CardPaymentMethod> findAllByMemberId(Long memberId) {
        return cardPaymentMethodRepository.findAllByMemberId(memberId);
    }

    public Optional<CardPaymentMethod> findById(Long id) {
        return cardPaymentMethodRepository.findById(id);
    }
}
