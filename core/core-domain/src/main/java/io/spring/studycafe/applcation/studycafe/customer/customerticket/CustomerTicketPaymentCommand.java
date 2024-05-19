package io.spring.studycafe.applcation.studycafe.customer.customerticket;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;

public record CustomerTicketPaymentCommand(
    Long studyCafeId,
    Long memberId,
    Long ticketId,
    PaymentMethodType paymentMethodType,
    Long paymentMethodId
) {
}
