package io.spring.studycafe.oauth2.naver.authorization;

import io.spring.studycafe.applcation.member.oauth2.OAuth2Platform;
import io.spring.studycafe.applcation.member.oauth2.authorizationurl.OAuth2AuthorizationUrlCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NaverAuthorizationUrlCreator implements OAuth2AuthorizationUrlCreator {
    @Value("${oauth2.naver.client-id}")
    private String clientId;

    @Value("${oauth2.naver.redirect-uri}")
    private String redirectUri;

    @Value("${oauth2.naver.authorization-server-url}")
    private String domain;

    private final String BASE_QUERY_STRING_FORMAT = "response_type=code&client_id=%s&state=%s&redirect_uri=%s";

    private String makeQueryString() {
        return String.format(BASE_QUERY_STRING_FORMAT, clientId, UUID.randomUUID(), redirectUri);
    }

    @Override
    public String create() {
        return new StringBuilder()
            .append(domain)
            .append("?")
            .append(makeQueryString())
            .toString();
    }

    @Override
    public OAuth2Platform getOAuth2Platform() {
        return OAuth2Platform.NAVER;
    }
}
