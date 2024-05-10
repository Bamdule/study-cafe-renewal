package io.spring.studycafe.presentation.member;

import io.spring.studycafe.applcation.member.MemberInfo;
import io.spring.studycafe.domain.member.RegistrationPlatform;

public record MemberResponse(Long id, String email, String name, long cash, RegistrationPlatform registrationPlatform) {

    public static MemberResponse of(MemberInfo memberInfo) {
        return new MemberResponse(memberInfo.id(), memberInfo.email(), memberInfo.name(), memberInfo.cash(), memberInfo.registrationPlatform());
    }
}
