package io.spring.studycafe.applcation.member.oauth2.adapter;

import io.spring.studycafe.applcation.member.oauth2.OAuth2Platform;

public record OAuth2AccessTokenRequest(String code, String state, OAuth2Platform platform) {
}
