package io.spring.studycafe.config.resolver;

import io.spring.studycafe.authorization.AuthorizationMetaData;
import io.spring.studycafe.authorization.AuthorizationPayload;
import io.spring.studycafe.config.handler.exception.AuthorizationExceptionCode;
import io.spring.studycafe.config.handler.exception.TokenAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(MemberInfo.class) &&
            parameter.hasParameterAnnotation(MemberAuthentication.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        AuthorizationPayload payload = getAuthorizationPayload(request);

        if (payload == null) {
            throw new TokenAuthorizationException(AuthorizationExceptionCode.INVALID_TOKEN);
        }

        return new MemberInfo(payload.id(), payload.email(), payload.name());
    }

    private AuthorizationPayload getAuthorizationPayload(HttpServletRequest request) {
        return (AuthorizationPayload) request.getAttribute(AuthorizationMetaData.AUTHENTICATION.name());
    }
}
