package io.spring.studycafe.authorization;

import io.spring.studycafe.authorization.jwt.TokenManager;

public class TokenAuthorizationManager implements AuthorizationManager {

    private final TokenManager tokenManager;

    public TokenAuthorizationManager(TokenAuthorizationManagerSetting setting) {
        this.tokenManager = new TokenManager(
            setting.accessTokenSecretKey(),
            setting.refreshTokenSecretKey(),
            setting.accessTokenExpiredMinutes(),
            setting.refreshTokenExpiredMinutes(),
            setting.objectMapper()
        );
    }

    @Override
    public AuthorizationToken generateAuthorization(AuthorizationPayload request) {
        AuthorizationToken token = tokenManager.generateToken(
            new AuthorizationPayload(
                request.id(),
                request.email(),
                request.name())
        );

        return new AuthorizationToken(token.accessToken(), token.refreshToken());
    }

    @Override
    public AuthorizationPayload validateAccessToken(String accessToken) {
        AuthorizationPayload payload = tokenManager.validateAccessToken(accessToken);
        return new AuthorizationPayload(
            payload.id(),
            payload.email(),
            payload.name()
        );
    }

    @Override
    public AuthorizationToken validateRefreshToken(String refreshToken) {

        // refreshToken을 검증
        AuthorizationPayload payload = tokenManager.validateRefreshToken(refreshToken);
        // 검증후 새로운 accessToken과 refreshToken을 생성한다
        AuthorizationToken token = tokenManager.generateToken(new AuthorizationPayload(payload.id(), payload.email(), payload.name()));
        return new AuthorizationToken(token.accessToken(), token.refreshToken());
    }


}
