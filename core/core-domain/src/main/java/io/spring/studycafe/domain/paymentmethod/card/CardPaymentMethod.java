package io.spring.studycafe.domain.paymentmethod.card;

import lombok.Getter;

@Getter
public class CardPaymentMethod {
    private Long id;
    private Long memberId;
    private String cardSecretKey;
    private CardPaymentAgency cardPaymentAgency;


    public CardPaymentMethod(Long id, Long memberId, String cardSecretKey, CardPaymentAgency cardPaymentAgency) {
        this.id = id;
        this.memberId = memberId;
        this.cardSecretKey = cardSecretKey;
        this.cardPaymentAgency = cardPaymentAgency;
    }

    public CardPaymentMethod(Long memberId, String cardSecretKey, CardPaymentAgency cardPaymentAgency) {
        this(null, memberId, cardSecretKey, cardPaymentAgency);
    }
}
