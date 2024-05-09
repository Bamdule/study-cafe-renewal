package io.spring.studycafe.entity.paymentmethod.pay;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayPaymentMethodJpaRepository extends JpaRepository<PayPaymentMethodEntity, Long> {

    Optional<PayPaymentMethodEntity> findByMemberId(Long memberId);
}
