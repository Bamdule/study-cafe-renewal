package io.spring.studycafe.applcation.member.oauth2;

public record OAuth2MemberRequest(String accessToken, OAuth2Platform platform) {

    public String getAccessTokenWithBearer() {
        return String.format("Bearer %s", accessToken);
    }
}
