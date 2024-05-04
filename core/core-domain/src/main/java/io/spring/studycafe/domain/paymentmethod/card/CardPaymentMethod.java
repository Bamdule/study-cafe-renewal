package io.spring.studycafe.domain.paymentmethod.card;

import lombok.Getter;

import java.util.Objects;

@Getter
public class CardPaymentMethod {
    private Long id;
    private Long memberId;
    private String cardSecretKey;
    private CardPaymentAgency cardPaymentAgency;
    private String lastDigits;


    public CardPaymentMethod(Long id, Long memberId, String cardSecretKey, String lastDigits, CardPaymentAgency cardPaymentAgency) {

        Objects.requireNonNull(memberId, "memberId is not null");
        Objects.requireNonNull(cardSecretKey, "cardSecretKey is not null");
        Objects.requireNonNull(lastDigits, "lastDigits is not null");
        Objects.requireNonNull(cardPaymentAgency, "cardPaymentAgency is not null");

        this.id = id;
        this.memberId = memberId;
        this.cardSecretKey = cardSecretKey;
        this.cardPaymentAgency = cardPaymentAgency;
        this.lastDigits = lastDigits;
    }

    public CardPaymentMethod(Long memberId, String cardSecretKey, String lastDigits, CardPaymentAgency cardPaymentAgency) {
        this(null, memberId, cardSecretKey, lastDigits, cardPaymentAgency);
    }
}
