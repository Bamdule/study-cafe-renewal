package io.spring.studycafe.presentation.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardPaymentMethodRegistrationRequest(
    @NotBlank(message = "카드 번호는 필수 값입니다.")
    String cardNumber,
    @NotBlank(message = "카드 만기연도는 필수 값입니다.")
    String expirationYear,
    @NotBlank(message = "카드 만기월은 필수 값입니다.")
    String expirationMonth,
    @NotBlank(message = "카드 비밀번호는 필수 값입니다.")
    String cardPassword,
    @NotBlank(message = "카드 생년월일/사업자번호는 필수 값입니다.")
    String personalId,
    @NotNull(message = "카드 결제 대행 업체는 필수 값입니다.")
    CardPaymentAgency cardPaymentAgency
) {
}
