package io.spring.studycafe.presentation.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CardPaymentMethodRegistrationRequest(
    @Pattern(regexp = "^[0-9]$", message = "카드 번호에는 숫자만 입력할 수 있습니다.")
    @NotBlank(message = "카드 번호는 필수 값입니다.")
    String cardNumber,

    @Pattern(regexp = "^[0-9]$", message = "카드 만기연도에는 숫자만 입력할 수 있습니다.")
    @NotBlank(message = "카드 만기연도는 필수 값입니다.")
    String expirationYear,
    @Pattern(regexp = "^[0-9]$", message = "카드 만기월에는 숫자만 입력할 수 있습니다.")
    @NotBlank(message = "카드 만기월은 필수 값입니다.")
    String expirationMonth,
    @NotBlank(message = "카드 뒷 2자리 비밀번호는 필수 값입니다.")
    String cardPassword,
    @NotBlank(message = "카드 생년월일/사업자번호는 필수 값입니다.")
    String personalId,
    @NotNull(message = "카드 결제 대행 업체는 필수 값입니다.")
    CardPaymentAgency cardPaymentAgency
) {
}
