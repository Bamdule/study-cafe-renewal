package io.spring.studycafe.entity.paymentmethod.pay;



import io.spring.studycafe.domain.paymentmethod.pay.PayPaymentMethod;
import io.spring.studycafe.domain.paymentmethod.pay.PayPaymentMethodRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PayPaymentMethodCoreRepository implements PayPaymentMethodRepository {

    private final PayPaymentMethodJpaRepository cardPaymentMethodJpaRepository;

    public PayPaymentMethodCoreRepository(PayPaymentMethodJpaRepository cardPaymentMethodJpaRepository) {
        this.cardPaymentMethodJpaRepository = cardPaymentMethodJpaRepository;
    }


    @Override
    public Optional<PayPaymentMethod> findById(Long id) {
        return cardPaymentMethodJpaRepository.findById(id).map(PayPaymentMethodEntity::toModel);
    }

    @Override
    public Optional<PayPaymentMethod> findByMemberId(String memberId) {
        return cardPaymentMethodJpaRepository.findByMemberId(memberId).map(PayPaymentMethodEntity::toModel);
    }

    @Override
    public PayPaymentMethod save(PayPaymentMethod cardPaymentMethod) {
        return cardPaymentMethodJpaRepository.save(PayPaymentMethodEntity.of(cardPaymentMethod)).toModel();
    }
}
