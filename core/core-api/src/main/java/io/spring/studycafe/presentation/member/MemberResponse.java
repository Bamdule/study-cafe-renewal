package io.spring.studycafe.presentation.member;

import io.spring.studycafe.domain.member.RegistrationPlatform;

public record MemberResponse(Long id, String email, String name, long cash, RegistrationPlatform registrationPlatform) {
}
