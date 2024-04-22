package io.spring.studycafe.entity.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardPaymentMethodJpaRepository extends JpaRepository<CardPaymentMethodEntity, Long> {

    Optional<CardPaymentMethodEntity> findByMemberId(Long memberId);
}
