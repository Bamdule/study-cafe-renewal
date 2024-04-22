package io.spring.studycafe.authorization.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.studycafe.authorization.AuthorizationPayload;
import io.spring.studycafe.authorization.AuthorizationToken;

public class TokenManager {
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;

    public TokenManager(String accessTokenSecretKey, String refreshTokenSecretKey, Long accessTokenExpiredMinutes, Long refreshTokenExpiredMinutes, ObjectMapper objectMapper) {
        this.accessTokenProvider = new AccessTokenProvider(accessTokenSecretKey, accessTokenExpiredMinutes, objectMapper);
        this.refreshTokenProvider = new RefreshTokenProvider(refreshTokenSecretKey, refreshTokenExpiredMinutes, objectMapper);
    }

    public AuthorizationToken generateToken(AuthorizationPayload payload) {
        return new AuthorizationToken(
            accessTokenProvider.generate(payload),
            refreshTokenProvider.generate(payload)
        );
    }

    public AuthorizationPayload validateAccessToken(String token) {
        return accessTokenProvider.validate(token);
    }

    public AuthorizationPayload validateRefreshToken(String token) {
        return refreshTokenProvider.validate(token);
    }

}