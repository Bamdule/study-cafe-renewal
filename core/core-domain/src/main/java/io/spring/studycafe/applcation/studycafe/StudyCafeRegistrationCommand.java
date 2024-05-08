package io.spring.studycafe.applcation.studycafe;

import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.studycafe.StudyCafe;

public record StudyCafeRegistrationCommand(String name, String address, String phoneNumber, Long memberId) {
    public StudyCafe toEntity(Member member) {
        return StudyCafe.initialize(name, address, phoneNumber, member.getId());
    }
}
