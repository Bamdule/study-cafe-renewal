package io.spring.studycafe.applcation.member.oauth2.authorizationurl;

import io.spring.studycafe.applcation.member.oauth2.OAuth2Platform;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuth2AuthorizationUrlProvider {
    private final Map<OAuth2Platform, OAuth2AuthorizationUrlCreator> urlCreator;

    public OAuth2AuthorizationUrlProvider(List<OAuth2AuthorizationUrlCreator> urlCreators) {
        this.urlCreator = urlCreators.stream().collect(Collectors.toMap(OAuth2AuthorizationUrlCreator::getOAuth2Platform, Function.identity()));
    }

    public OAuth2AuthorizationUrlCreator provide(OAuth2Platform platform) {
        return urlCreator.get(platform);
    }
}
