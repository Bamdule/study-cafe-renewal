package io.spring.studycafe.applcation.member.oauth2;

import io.spring.studycafe.domain.member.RegistrationPlatform;

public enum OAuth2Platform {
    NAVER(RegistrationPlatform.NAVER), KAKAO(RegistrationPlatform.KAKAO), GOOGLE(RegistrationPlatform.GOOGLE);

    private final RegistrationPlatform registrationPlatform;

    OAuth2Platform(RegistrationPlatform registrationPlatform) {
        this.registrationPlatform = registrationPlatform;
    }

    public RegistrationPlatform getRegistrationPlatform() {
        return registrationPlatform;
    }
}
