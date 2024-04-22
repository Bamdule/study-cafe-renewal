package io.spring.studycafe.authorization.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.studycafe.authorization.AuthorizationPayload;
import io.spring.studycafe.authorization.AuthorizationToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TokenManagerTest {
    private TokenManager tokenManager = new TokenManager(
        "c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwA",
        "c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwB",
        10L,
        20L,
        new ObjectMapper()
    );

    @Test
    public void generate_token_테스트() {
        AuthorizationToken authorizationToken = tokenManager.generateToken(new AuthorizationPayload(1L, "email@test.com", "good"));
        Assertions.assertThat(authorizationToken).isNotNull();
        Assertions.assertThat(authorizationToken.accessToken()).isNotBlank();
        Assertions.assertThat(authorizationToken.refreshToken()).isNotBlank();
    }

    @Test
    public void token_복호화_테스트() {
        AuthorizationToken authorizationToken = tokenManager.generateToken(new AuthorizationPayload(1L, "email@test.com", "good"));
        Assertions.assertThat(authorizationToken).isNotNull();
        Assertions.assertThat(authorizationToken.accessToken()).isNotBlank();
        Assertions.assertThat(authorizationToken.refreshToken()).isNotBlank();

        AuthorizationPayload accessTokenAuthorizationPayload = tokenManager.validateAccessToken(authorizationToken.accessToken());

        AuthorizationPayload refreshTokenAuthorizationPayload = tokenManager.validateRefreshToken(authorizationToken.refreshToken());

        Assertions.assertThat(accessTokenAuthorizationPayload).isNotNull();
        Assertions.assertThat(accessTokenAuthorizationPayload.id()).isEqualTo(1L);
        Assertions.assertThat(accessTokenAuthorizationPayload.email()).isEqualTo("email@test.com");
        Assertions.assertThat(accessTokenAuthorizationPayload.name()).isEqualTo("good");

        Assertions.assertThat(refreshTokenAuthorizationPayload).isNotNull();
        Assertions.assertThat(refreshTokenAuthorizationPayload.id()).isEqualTo(1L);
        Assertions.assertThat(refreshTokenAuthorizationPayload.email()).isEqualTo("email@test.com");
        Assertions.assertThat(refreshTokenAuthorizationPayload.name()).isEqualTo("good");

    }
}