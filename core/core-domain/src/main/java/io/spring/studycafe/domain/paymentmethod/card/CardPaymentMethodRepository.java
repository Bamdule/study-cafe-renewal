package io.spring.studycafe.domain.paymentmethod.card;

import java.util.Optional;

public interface CardPaymentMethodRepository {
    Optional<CardPaymentMethod> findById(Long id);

    Optional<CardPaymentMethod> findByMemberId(Long memberId);

    CardPaymentMethod save(CardPaymentMethod cardPaymentMethod);
}
