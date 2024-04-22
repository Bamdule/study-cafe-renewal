package io.spring.studycafe.paymentmethod.card.nicepay;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;


@Getter
@ConfigurationProperties(prefix = "nicepay")
public class NicePayProperties {

    private final String domain;

    private final String basicAuthorization;

    private final String clientKey;

    private final String secretKey;

    public NicePayProperties(String domain, String basicAuthorization, String clientKey, String secretKey) {
        Objects.requireNonNull(domain, "domain is required");
        Objects.requireNonNull(basicAuthorization, "basicAuthorization is required");
        Objects.requireNonNull(clientKey, "clientKey is required");
        Objects.requireNonNull(secretKey, "secretKey is required");


        this.domain = domain;
        this.basicAuthorization = basicAuthorization;
        this.clientKey = clientKey;
        this.secretKey = secretKey;
    }
}
