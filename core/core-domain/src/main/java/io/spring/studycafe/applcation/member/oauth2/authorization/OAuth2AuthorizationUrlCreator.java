package io.spring.studycafe.applcation.member.oauth2.authorization;

import io.spring.studycafe.applcation.member.oauth2.OAuth2Platform;

public interface OAuth2AuthorizationUrlCreator {
    String create();

    OAuth2Platform getOAuth2Platform();
}
