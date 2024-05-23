package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.applcation.payment.PaymentResult;

public record TicketPaymentResponse(
    Long paymentId,

    String itemName,
    Long itemPrice,

    String message,
    String resultCode,
    String resultType,
    boolean success
) {
    public static TicketPaymentResponse of(PaymentResult paymentResult) {
        return new TicketPaymentResponse(
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
