package io.spring.studycafe.domain.paymentmethod;

import lombok.Getter;

@Getter
public class PaymentMethod{
    private Long id;
    private Long memberId;
    private PaymentMethodType type;


    public PaymentMethod(Long id, Long memberId, PaymentMethodType type) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
    }

    public PaymentMethod(Long memberId, PaymentMethodType type) {
        this.memberId = memberId;
        this.type = type;
    }
}
