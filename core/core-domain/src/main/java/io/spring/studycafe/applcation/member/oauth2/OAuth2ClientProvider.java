package io.spring.studycafe.applcation.member.oauth2;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuth2ClientProvider {

    private final Map<OAuth2Platform, OAuth2Client> clients;

    public OAuth2ClientProvider(List<OAuth2Client> oAuth2Clients) {
        this.clients = oAuth2Clients.stream().collect(Collectors.toMap(OAuth2Client::getOAuth2Platform, Function.identity()));
    }

    public OAuth2Client provide(OAuth2Platform platform) {
        return clients.get(platform);
    }
}
