package io.spring.studycafe.config.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.studycafe.authorization.AuthorizationManager;
import io.spring.studycafe.authorization.TokenAuthorizationManager;
import io.spring.studycafe.authorization.TokenAuthorizationManagerSetting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationConfig {

    private final ObjectMapper objectMapper;

    @Value("${authorization.jwt.access-token.secret-key}")
    private String accessTokenSecretKey;

    @Value("${authorization.jwt.refresh-token.secret-key}")
    private String refreshTokenSecretKey;

    @Value("${authorization.jwt.access-token.expired-minutes}")
    private Long accessTokenExpiredMinutes;

    @Value("${authorization.jwt.refresh-token.expired-minutes}")
    private Long refreshTokenExpiredMinutes;

    public AuthorizationConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public AuthorizationManager authorizationManager() {
        return new TokenAuthorizationManager(new TokenAuthorizationManagerSetting(
            accessTokenSecretKey,
            refreshTokenSecretKey,
            accessTokenExpiredMinutes,
            refreshTokenExpiredMinutes,
            objectMapper
        ));
    }

}
