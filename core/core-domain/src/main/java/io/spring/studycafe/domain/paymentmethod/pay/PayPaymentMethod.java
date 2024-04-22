package io.spring.studycafe.domain.paymentmethod.pay;

import lombok.Getter;

@Getter
public class PayPaymentMethod {
    private Long id;
    private String memberId;
    private String paySecretKey;

    public PayPaymentMethod(Long id, String memberId, String paySecretKey) {
        this.id = id;
        this.memberId = memberId;
        this.paySecretKey = paySecretKey;
    }
}
