package io.spring.studycafe.applcation.member;

import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.RegistrationPlatform;

public record MemberInfo(
    Long id,
    String name,
    String email,
    Long cash,
    RegistrationPlatform registrationPlatform
) {

    public static MemberInfo of(Member member) {
        return new MemberInfo(member.getId(), member.getName(), member.getEmail(), member.getMemberCash().getCash(), member.getRegistrationPlatform());
    }
}
