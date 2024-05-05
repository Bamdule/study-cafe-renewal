package io.spring.studycafe.applcation.member.oauth2.adapter;

import org.springframework.stereotype.Service;

@Service
public class OAuth2ClientAdapter {
    private final OAuth2ClientProvider oAuth2ClientProvider;

    public OAuth2ClientAdapter(OAuth2ClientProvider oAuth2ClientProvider) {
        this.oAuth2ClientProvider = oAuth2ClientProvider;
    }

    public OAuth2AccessTokenResponse requestAccessToken(OAuth2AccessTokenRequest request) {
        if (request.code() == null || request.code() == "") {
            throw new RuntimeException("code is required");
        }

        if (request.state() == null || request.state() == "") {
            throw new RuntimeException("state is required");
        }

        if (request.platform() == null) {
            throw new RuntimeException("platform is required");
        }

        OAuth2AccessTokenResponse response = oAuth2ClientProvider.provide(request.platform())
            .requestAccessToken(request);

        if (!response.success()) {
            throw new RuntimeException(response.errorDescription());
        }

        return response;
    }

    public OAuth2MemberResponse requestMember(OAuth2MemberRequest request) {

        if (request.accessToken() == null || request.accessToken() == "") {
            throw new RuntimeException("accessToken is required");
        }


        return oAuth2ClientProvider.provide(request.platform())
            .requestMember(request);
    }

}
