package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.domain.payment.Payment;
import io.spring.studycafe.domain.payment.PaymentResultType;

public record PaymentInfo(
    Long id,
    Long studyCafeId,
    Long memberId,
    Long customerId,
    Long orderId,
    Long paymentMethodId,
    String name,
    Long totalPrice,
    String message,
    String orderCode,
    String resultCode,
    PaymentResultType resultType
) {
    public static PaymentInfo create(Payment payment) {
        return new PaymentInfo(
            payment.getId(),
            payment.getStudyCafeId(),
            payment.getMemberId(),
            payment.getCustomerId(),
            payment.getOrderId(),
            payment.getPaymentMethodId(),
            payment.getName(),
            payment.getTotalPrice(),
            payment.getMessage(),
            payment.getOrderCode(),
            payment.getResultCode(),
            payment.getResultType()
        );
    }

}
