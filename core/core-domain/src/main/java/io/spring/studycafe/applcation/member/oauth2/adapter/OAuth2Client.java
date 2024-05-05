package io.spring.studycafe.applcation.member.oauth2.adapter;

import io.spring.studycafe.applcation.member.oauth2.OAuth2Platform;

public interface OAuth2Client {

    OAuth2AccessTokenResponse requestAccessToken(OAuth2AccessTokenRequest request);

    OAuth2MemberResponse requestMember(OAuth2MemberRequest request);

    OAuth2Platform getOAuth2Platform();
}
