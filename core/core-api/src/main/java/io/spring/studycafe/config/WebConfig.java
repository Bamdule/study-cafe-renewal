package io.spring.studycafe.config;

import io.spring.studycafe.authorization.AuthorizationManager;
import io.spring.studycafe.config.interceptor.AuthorizationHandlerInterceptor;
import io.spring.studycafe.config.resolver.AuthorizationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthorizationManager authorizationManager;

    public WebConfig(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationHandlerInterceptor(authorizationManager))
            .excludePathPatterns("/api/v1/oauth2-member-registration/**", "/favicon.ico", "/error","/api/v1/redis-test/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthorizationArgumentResolver());
    }
}
