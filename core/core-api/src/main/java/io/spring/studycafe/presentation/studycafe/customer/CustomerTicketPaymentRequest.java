package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import jakarta.validation.constraints.NotNull;

public record CustomerTicketPaymentRequest(
    @NotNull(message = "스터디 카페 아이디는 필수 값입니다.")
    Long studyCafeId,
    @NotNull(message = "이용권 아이디는 필수 값입니다.")
    Long ticketId,

    @NotNull(message = "결제수단 타입은 값입니다.")
    PaymentMethodType paymentMethodType,

    @NotNull(message = "결제수단 아이디는 필수 값입니다.")
    Long paymentMethodId
) {
}
