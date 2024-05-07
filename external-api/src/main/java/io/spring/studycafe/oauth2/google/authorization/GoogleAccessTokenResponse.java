package io.spring.studycafe.oauth2.google.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.spring.studycafe.applcation.member.oauth2.adapter.OAuth2AccessTokenResponse;

public record GoogleAccessTokenResponse(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("refresh_token") String refreshToken,
    @JsonProperty("token_type") String tokenType,
    @JsonProperty("expires_in") String expiresIn,
    @JsonProperty("error") String error,
    @JsonProperty("error_description") String errorDescription
) {
    public static OAuth2AccessTokenResponse failure(String message) {
        return new OAuth2AccessTokenResponse(
            "",
            "",
            "",
            "",
            "error",
            message,
            false
        );
    }

    public boolean isSuccess() {
        return error == null;
    }

    public OAuth2AccessTokenResponse to() {

        return new OAuth2AccessTokenResponse(
            accessToken,
            refreshToken,
            tokenType,
            expiresIn,
            error,
            errorDescription,
            isSuccess()
        );
    }
}
