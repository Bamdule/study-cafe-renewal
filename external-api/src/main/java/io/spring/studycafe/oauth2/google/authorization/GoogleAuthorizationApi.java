package io.spring.studycafe.oauth2.google.authorization;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GoogleAuthorizationApi {
    @FormUrlEncoded
    @POST("/token")
    Call<GoogleAccessTokenResponse> requestAccessToken(
        @Field(value = "grant_type") String grantType,
        @Field(value = "client_id") String clientId,
        @Field(value = "client_secret") String clientSecret,
        @Field(value = "code") String code,
        @Field(value = "redirect_uri") String redirectUri
    );
}
