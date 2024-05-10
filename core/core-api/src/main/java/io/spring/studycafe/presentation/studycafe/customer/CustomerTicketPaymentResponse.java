package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.payment.PaymentResult;

public record CustomerTicketPaymentResponse(
    Long paymentId,

    String itemName,
    Long itemPrice,

    String message,
    String resultCode,
    String resultType,
    boolean success
) {
    public static CustomerTicketPaymentResponse of(PaymentResult paymentResult) {
        return new CustomerTicketPaymentResponse(
            paymentResult.paymentId(),
            paymentResult.itemName(),
            paymentResult.itemPrice(),
            paymentResult.message(),
            paymentResult.resultCode(),
            paymentResult.resultType(),
            paymentResult.success()
        );

    }

}
