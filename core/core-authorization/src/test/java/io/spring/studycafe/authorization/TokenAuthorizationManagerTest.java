package io.spring.studycafe.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TokenAuthorizationManagerTest {

    TokenAuthorizationManager tokenAuthorizationManager = new TokenAuthorizationManager(
        new TokenAuthorizationManagerSetting(
            "c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwA",
            "c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwB",
            10L,
            20L,
            new ObjectMapper()
        )
    );


    @Test
    void generateAuthorization_test() {
        AuthorizationToken authorizationToken = tokenAuthorizationManager.generateAuthorization(new AuthorizationPayload(1L, "email", "name"));
        Assertions.assertThat(authorizationToken.accessToken()).isNotBlank();
        Assertions.assertThat(authorizationToken.refreshToken()).isNotBlank();
    }

    @Test
    void validateAccessToken_and_refreshToken_test() {
        AuthorizationToken authorizationToken = tokenAuthorizationManager.generateAuthorization(new AuthorizationPayload(1L, "email", "name"));
        Assertions.assertThat(authorizationToken.accessToken()).isNotBlank();
        Assertions.assertThat(authorizationToken.refreshToken()).isNotBlank();
        AuthorizationPayload authorizationPayload = tokenAuthorizationManager.validateAccessToken(authorizationToken.accessToken());
        AuthorizationToken refreshedToken = tokenAuthorizationManager.validateRefreshToken(authorizationToken.refreshToken());

        Assertions.assertThat(authorizationPayload).isNotNull();

        Assertions.assertThat(refreshedToken).isNotNull();
    }
}