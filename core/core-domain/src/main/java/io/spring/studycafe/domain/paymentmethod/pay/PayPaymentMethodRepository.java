package io.spring.studycafe.domain.paymentmethod.pay;

import java.util.Optional;

public interface PayPaymentMethodRepository {
    Optional<PayPaymentMethod> findById(Long id);

    Optional<PayPaymentMethod> findByMemberId(String memberId);

    PayPaymentMethod save(PayPaymentMethod payPaymentMethod);


}
