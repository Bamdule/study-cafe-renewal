package io.spring.studycafe.applcation.member.oauth2.adapter;

public record OAuth2AccessTokenResponse(
    String accessToken,
    String refreshToken,
    String tokenType,
    String expiresIn,
    String error,
    String errorDescription,
    boolean success
) {
    public static OAuth2AccessTokenResponse failure(String errorDescription) {
        return new OAuth2AccessTokenResponse("", "", "", "", "error", errorDescription, false);
    }
}
