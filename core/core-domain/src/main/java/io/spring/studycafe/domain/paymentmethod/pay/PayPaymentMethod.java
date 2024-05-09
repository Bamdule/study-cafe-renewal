package io.spring.studycafe.domain.paymentmethod.pay;

import io.spring.studycafe.domain.paymentmethod.PaymentMethod;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import lombok.Getter;

@Getter
public class PayPaymentMethod extends PaymentMethod {
    private Long id;
    private Long memberId;
    private String paySecretKey;

    public PayPaymentMethod(Long id, Long memberId, String paySecretKey) {
        super(id, memberId, PaymentMethodType.PAY);
        this.id = id;
        this.memberId = memberId;
        this.paySecretKey = paySecretKey;
    }

    public PayPaymentMethod(Long memberId, String paySecretKey) {
        this(null, memberId, paySecretKey);
    }
}
