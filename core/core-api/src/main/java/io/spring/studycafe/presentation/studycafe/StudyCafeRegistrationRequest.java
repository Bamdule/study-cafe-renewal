package io.spring.studycafe.presentation.studycafe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StudyCafeRegistrationRequest(
    @NotBlank(message = "스터디 카페 이름은 필수 값입니다.")
    String name,
    @NotBlank(message = "스터디 카페 주소는 필수 값입니다.")
    String address,
    @NotBlank(message = "휴대폰 번호는 필수 값입니다.")
    @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 '-'를 제외하고 10자리 ~ 11자리사이로 입력해주세요.")
    String phoneNumber
) {
}
