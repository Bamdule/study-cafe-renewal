package io.spring.studycafe.applcation.member;

import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import io.spring.studycafe.domain.member.cash.MemberCash;

public record MemberRegistrationCommand(String email, String name, RegistrationPlatform registrationPlatform) {
    public Member toEntity() {
        return new Member(email, name, registrationPlatform);
    }
}
