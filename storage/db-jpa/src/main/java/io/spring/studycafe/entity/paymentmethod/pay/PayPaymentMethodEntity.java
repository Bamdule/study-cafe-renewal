package io.spring.studycafe.entity.paymentmethod.pay;

import io.spring.studycafe.domain.paymentmethod.pay.PayPaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "pay_payment_method")
@Entity
public class PayPaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id", nullable = false, unique = true)
    private String memberId;

    @Column(name = "pay_secret_key", nullable = false)
    private String paySecretKey;

    public PayPaymentMethodEntity(Long id, String memberId, String paySecretKey) {
        this.id = id;
        this.memberId = memberId;
        this.paySecretKey = paySecretKey;
    }

    protected PayPaymentMethodEntity() {
    }

    public PayPaymentMethod toModel() {
        return new PayPaymentMethod(
            id,
            memberId,
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
