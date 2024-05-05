package io.spring.studycafe.applcation.member.oauth2.adapter;

import io.spring.studycafe.applcation.member.oauth2.OAuth2Platform;

public record OAuth2MemberRequest(String accessToken, OAuth2Platform platform) {

    public String getAccessTokenWithBearer() {
        return String.format("Bearer %s", accessToken);
    }
}
