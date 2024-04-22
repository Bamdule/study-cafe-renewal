package io.spring.studycafe.config.interceptor;

import io.spring.studycafe.authorization.AuthorizationManager;
import io.spring.studycafe.authorization.AuthorizationMetaData;
import io.spring.studycafe.authorization.AuthorizationPayload;
import io.spring.studycafe.authorization.jwt.exception.AuthorizationException;
import io.spring.studycafe.config.handler.exception.AuthorizationExceptionCode;
import io.spring.studycafe.config.handler.exception.TokenAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationHandlerInterceptor implements HandlerInterceptor {

    private final AuthorizationManager authorizationManager;

    public AuthorizationHandlerInterceptor(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = request.getHeader(AuthorizationMetaData.ACCESS_TOKEN.name());

        if (accessToken == null || accessToken == "") {
            throw new TokenAuthorizationException(AuthorizationExceptionCode.ACCESS_TOKEN_REQUIRED);
        }

        try {
            AuthorizationPayload payload = authorizationManager.validateAccessToken(accessToken);
            setAuthentication(request, payload);
        } catch (AuthorizationException exception) {
            throw new TokenAuthorizationException(exception.getMessage(), exception.getCode(), HttpStatus.UNAUTHORIZED.value());
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void setAuthentication(HttpServletRequest request, AuthorizationPayload payload) {
        request.setAttribute(AuthorizationMetaData.AUTHENTICATION.name(), payload);
    }
}
