package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import jakarta.validation.constraints.NotNull;

public record TicketPaymentRequest(
    @NotNull(message = "스터디 카페 아이디는 필수 값입니다.")
    Long studyCafeId,
    @NotNull(message = "결제수단 타입은 값입니다.")
    PaymentMethodType paymentMethodType,

    @NotNull(message = "결제수단 아이디는 필수 값입니다.")
    Long paymentMethodId
) {
}
