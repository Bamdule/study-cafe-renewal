package io.spring.studycafe.oauth2.google.resource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleResourceApi {
    @GET("/userinfo/v2/me")
    Call<GoogleMemberResponse> requestMemberInfo(
        @Query("access_token") String authorization
    );
}
