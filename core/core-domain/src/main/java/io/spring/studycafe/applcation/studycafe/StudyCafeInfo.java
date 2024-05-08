package io.spring.studycafe.applcation.studycafe;

import io.spring.studycafe.domain.studycafe.StudyCafe;

public record StudyCafeInfo(
    Long id,
    String name,
    String address,
    String phoneNumber,
    Long memberId
) {

    public static StudyCafeInfo of(StudyCafe studyCafe) {
        return new StudyCafeInfo(
            studyCafe.getId(),
            studyCafe.getName(),
            studyCafe.getAddress(),
            studyCafe.getPhoneNumber(),
            studyCafe.getMemberId()
        );
    }
}
