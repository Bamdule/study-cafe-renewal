package io.spring.studycafe.entity.paymentmethod.pay;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import io.spring.studycafe.domain.paymentmethod.pay.PayPaymentMethod;
import io.spring.studycafe.entity.paymentmethod.PaymentMethodEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@DiscriminatorValue("PAY")
@Table(name = "pay_payment_method")
@Entity
public class PayPaymentMethodEntity extends PaymentMethodEntity {

    @Column(name = "pay_secret_key", nullable = false)
    private String paySecretKey;

    public PayPaymentMethodEntity(Long id, Long memberId, String paySecretKey) {
        super(id, memberId);
        this.paySecretKey = paySecretKey;
    }

    protected PayPaymentMethodEntity() {
    }

    public PayPaymentMethod toModel() {
        return new PayPaymentMethod(
            getId(),
            getMemberId(),
            paySecretKey
        );
    }

    public static PayPaymentMethodEntity of(PayPaymentMethod payPaymentMethod) {
        return new PayPaymentMethodEntity(
            payPaymentMethod.getId(),
            payPaymentMethod.getMemberId(),
            payPaymentMethod.getPaySecretKey()
        );
    }
}
