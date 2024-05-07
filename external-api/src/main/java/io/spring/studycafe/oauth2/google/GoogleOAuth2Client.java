package io.spring.studycafe.oauth2.google;

import io.spring.studycafe.applcation.member.oauth2.*;
import io.spring.studycafe.applcation.member.oauth2.adapter.*;
import io.spring.studycafe.oauth2.google.authorization.GoogleAccessTokenResponse;
import io.spring.studycafe.oauth2.google.authorization.GoogleAuthorizationApi;
import io.spring.studycafe.oauth2.google.resource.GoogleMemberResponse;
import io.spring.studycafe.oauth2.google.resource.GoogleResourceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Component
public class GoogleOAuth2Client implements OAuth2Client {

    @Value("${oauth2.google.client-id}")
    private String clientId;

    @Value("${oauth2.google.client-secret}")
    private String clientSecret;

    @Value("${oauth2.google.redirect-uri}")
    private String redirectUri;
    private final String grantType = "authorization_code";
    private final GoogleAuthorizationApi googleAuthorizationApi;
    private final GoogleResourceApi googleResourceApi;

    public GoogleOAuth2Client(
        @Qualifier(value = "googleAuthorizationApi") GoogleAuthorizationApi googleOauth2Api,
        @Qualifier(value = "googleResourceApi") GoogleResourceApi googleResourceApi
    ) {
        this.googleAuthorizationApi = googleOauth2Api;
        this.googleResourceApi = googleResourceApi;
    }

    @Override
    public OAuth2AccessTokenResponse requestAccessToken(OAuth2AccessTokenRequest request) {

        Call<GoogleAccessTokenResponse> call = googleAuthorizationApi.requestAccessToken(
            grantType,
            clientId,
            clientSecret,
            request.code(),
            redirectUri
        );
        try {
            Response<GoogleAccessTokenResponse> response = call.execute();

            if (response.isSuccessful()) {
                return response.body().to();
            } else {
                return GoogleAccessTokenResponse.failure(response.message());
            }
        } catch (IOException e) {
            return GoogleAccessTokenResponse.failure(e.getMessage());
        }
    }

    @Override
    public OAuth2MemberResponse requestMember(OAuth2MemberRequest request) {
        Call<GoogleMemberResponse> call = googleResourceApi.requestMemberInfo(request.accessToken());

        try {
            Response<GoogleMemberResponse> response = call.execute();

            if (response.isSuccessful()) {
                return response.body().to();
            } else {
                return GoogleMemberResponse.failure(response.message());
            }
        } catch (IOException e) {
            return GoogleMemberResponse.failure(e.getMessage());
        }

    }

    @Override
    public OAuth2Platform getOAuth2Platform() {
        return OAuth2Platform.GOOGLE;
    }
}
