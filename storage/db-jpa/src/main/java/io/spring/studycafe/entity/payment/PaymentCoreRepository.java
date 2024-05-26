package io.spring.studycafe.entity.payment;

import io.spring.studycafe.domain.payment.Payment;
import io.spring.studycafe.domain.payment.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentCoreRepository implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentCoreRepository(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }


    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(PaymentEntity.create(payment)).toModel();
    }
}
