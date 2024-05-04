package io.spring.studycafe.entity.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethodRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardPaymentMethodCoreRepository implements CardPaymentMethodRepository {

    private final CardPaymentMethodJpaRepository cardPaymentMethodJpaRepository;

    public CardPaymentMethodCoreRepository(CardPaymentMethodJpaRepository cardPaymentMethodJpaRepository) {
        this.cardPaymentMethodJpaRepository = cardPaymentMethodJpaRepository;
    }


    @Override
    public Optional<CardPaymentMethod> findById(Long id) {
        return cardPaymentMethodJpaRepository.findById(id).map(CardPaymentMethodEntity::toModel);
    }

    @Override
    public CardPaymentMethod save(CardPaymentMethod cardPaymentMethod) {
        return cardPaymentMethodJpaRepository.save(CardPaymentMethodEntity.of(cardPaymentMethod)).toModel();
    }

    @Override
    public List<CardPaymentMethod> findAllByMemberId(Long memberId) {
        return cardPaymentMethodJpaRepository.findAllByMemberId(memberId)
            .stream().map(CardPaymentMethodEntity::toModel).toList();
    }

    @Override
    public void delete(CardPaymentMethod cardPaymentMethod) {
        cardPaymentMethodJpaRepository.deleteById(cardPaymentMethod.getId());
    }
}
