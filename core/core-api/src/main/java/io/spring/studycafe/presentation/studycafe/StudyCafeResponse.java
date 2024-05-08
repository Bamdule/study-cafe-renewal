package io.spring.studycafe.presentation.studycafe;

public record StudyCafeResponse(
    Long id,
    String name,
    String address,
    String phoneNumber,
    Long memberId
) {
}
