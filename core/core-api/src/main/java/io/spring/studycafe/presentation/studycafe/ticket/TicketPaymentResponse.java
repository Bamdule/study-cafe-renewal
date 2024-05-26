package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.applcation.payment.PaymentInfo;
import io.spring.studycafe.domain.payment.PaymentResultType;

public record TicketPaymentResponse(
    Long paymentId,

    String itemName,
    Long itemPrice,

    String message,
    String resultCode,
    PaymentResultType resultType
) {
    public static TicketPaymentResponse of(PaymentInfo paymentInfo) {
        return new TicketPaymentResponse(
            paymentInfo.id(),
            paymentInfo.name(),
            paymentInfo.totalPrice(),
            paymentInfo.message(),
            paymentInfo.resultCode(),
            paymentInfo.resultType()
        );

    }

}
