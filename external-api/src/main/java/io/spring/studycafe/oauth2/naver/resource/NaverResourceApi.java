package io.spring.studycafe.oauth2.naver.resource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NaverResourceApi {
    @GET("/v1/nid/me")
    Call<NaverMemberResponse> requestMemberInfo(
        @Header("Authorization") String authorization
    );
}
