package io.spring.studycafe.entity.paymentmethod.card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardPaymentMethodJpaRepository extends JpaRepository<CardPaymentMethodEntity, Long> {

    Optional<CardPaymentMethodEntity> findByMemberId(Long memberId);

    List<CardPaymentMethodEntity> findAllByMemberId(Long memberId);
}
