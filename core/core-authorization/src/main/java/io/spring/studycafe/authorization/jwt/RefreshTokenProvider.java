package io.spring.studycafe.authorization.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.studycafe.authorization.AuthorizationPayload;

public class RefreshTokenProvider extends AbstractTokenProvider<AuthorizationPayload> {
    public RefreshTokenProvider(String secretKey, Long expiredMinutes, ObjectMapper objectMapper) {
        super(secretKey, expiredMinutes, objectMapper);
    }

    @Override
    protected Class<AuthorizationPayload> getPayloadClassType() {
        return AuthorizationPayload.class;
    }
}
