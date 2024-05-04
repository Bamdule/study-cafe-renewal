package io.spring.studycafe.domain.paymentmethod.card;

import java.util.List;
import java.util.Optional;

public interface CardPaymentMethodRepository {
    Optional<CardPaymentMethod> findById(Long id);

    CardPaymentMethod save(CardPaymentMethod cardPaymentMethod);

    List<CardPaymentMethod> findAllByMemberId(Long memberId);

    void delete(CardPaymentMethod cardPaymentMethod);
}
