package io.spring.studycafe.authorization.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.security.WeakKeyException;
import io.spring.studycafe.authorization.AuthorizationPayload;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccessTokenProviderTest {

    private AccessTokenProvider accessTokenProvider = new AccessTokenProvider("c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwK", 20L, new ObjectMapper());

    @Test
    public void accessTokenProvider_Secret_key_없을경우_예외() {
        Assertions
            .assertThatThrownBy(() -> new AccessTokenProvider("", 20L, new ObjectMapper())).isInstanceOf(WeakKeyException.class);
    }

    @Test
    public void access_token_생성_테스트() {
        String token = accessTokenProvider.generate(new AuthorizationPayload(1L, "", "good"));
        Assertions.assertThat(token).isNotBlank();
    }

    @Test
    public void access_token_복호화_테스트() {
        String token = accessTokenProvider.generate(new AuthorizationPayload(1L, "test@test.com", "good"));
        Assertions.assertThat(token).isNotBlank();
        AuthorizationPayload payload = accessTokenProvider.validate(token);

        Assertions.assertThat(payload.id()).isEqualTo(1L);
        Assertions.assertThat(payload.email()).isEqualTo("test@test.com");
        Assertions.assertThat(payload.name()).isEqualTo("good");
    }

}