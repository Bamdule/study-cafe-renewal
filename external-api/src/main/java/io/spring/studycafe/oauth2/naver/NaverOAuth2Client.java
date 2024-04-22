package io.spring.studycafe.oauth2.naver;

import io.spring.studycafe.applcation.member.oauth2.*;
import io.spring.studycafe.oauth2.naver.authorization.NaverAccessTokenResponse;
import io.spring.studycafe.oauth2.naver.authorization.NaverAuthorizationApi;
import io.spring.studycafe.oauth2.naver.resource.NaverMemberResponse;
import io.spring.studycafe.oauth2.naver.resource.NaverResourceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Component
public class NaverOAuth2Client implements OAuth2Client {

    @Value("${oauth2.naver.client-id}")
    private String clientId;

    @Value("${oauth2.naver.client-secret}")
    private String clientSecret;
    private final String grantType = "authorization_code";
    private final NaverAuthorizationApi naverAuthorizationApi;
    private final NaverResourceApi naverResourceApi;

    public NaverOAuth2Client(
        @Qualifier(value = "naverAuthorizationApi") NaverAuthorizationApi naverOauth2Api,
        @Qualifier(value = "naverResourceApi") NaverResourceApi naverResourceApi
    ) {
        this.naverAuthorizationApi = naverOauth2Api;
        this.naverResourceApi = naverResourceApi;
    }

    @Override
    public OAuth2AccessTokenResponse requestAccessToken(OAuth2AccessTokenRequest request) {

        Call<NaverAccessTokenResponse> call = naverAuthorizationApi.requestAccessToken(
            grantType,
            clientId,
            clientSecret,
            request.state(),
            request.code()
        );
        try {
            Response<NaverAccessTokenResponse> response = call.execute();

            if (response.isSuccessful()) {
                return response.body().to();
            } else {
                return NaverAccessTokenResponse.failure(response.message());
            }
        } catch (IOException e) {
            return NaverAccessTokenResponse.failure(e.getMessage());
        }
    }

    @Override
    public OAuth2MemberResponse requestMember(OAuth2MemberRequest request) {
        Call<NaverMemberResponse> call = naverResourceApi.requestMemberInfo(request.getAccessTokenWithBearer());

        try {
            Response<NaverMemberResponse> response = call.execute();

            if (response.isSuccessful()) {
                return response.body().to();
            } else {
                return NaverMemberResponse.failure(response.message());
            }
        } catch (IOException e) {
            return NaverMemberResponse.failure(e.getMessage());
        }

    }

    @Override
    public OAuth2Platform getOAuth2Platform() {
        return OAuth2Platform.NAVER;
    }
}
