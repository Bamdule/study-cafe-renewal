package io.spring.studycafe.applcation.member.oauth2;

public record OAuth2AccessTokenRequest(String code, String state, OAuth2Platform platform) {
}
