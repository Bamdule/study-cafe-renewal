package io.spring.studycafe.oauth2.naver.authorization;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NaverAuthorizationApi {
    @FormUrlEncoded
    @POST("/oauth2.0/token")
    Call<NaverAccessTokenResponse> requestAccessToken(
        @Field(value = "grant_type") String grantType,
        @Field(value = "client_id") String clientId,
        @Field(value = "client_secret") String clientSecret,
        @Field(value = "state") String state,
        @Field(value = "code") String code
    );
}
