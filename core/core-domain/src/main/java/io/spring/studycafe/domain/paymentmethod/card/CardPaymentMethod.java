package io.spring.studycafe.domain.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.PaymentMethod;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CardPaymentMethod extends PaymentMethod {

    private final String cardSecretKey;
    private final CardPaymentAgency cardPaymentAgency;
    private final String lastDigits;


    public CardPaymentMethod(Long id, Long memberId, String cardSecretKey, String lastDigits, CardPaymentAgency cardPaymentAgency) {
        super(id, memberId, PaymentMethodType.CARD);
        Objects.requireNonNull(memberId, "memberId is not null");
        Objects.requireNonNull(cardSecretKey, "cardSecretKey is not null");
        Objects.requireNonNull(lastDigits, "lastDigits is not null");
        Objects.requireNonNull(cardPaymentAgency, "cardPaymentAgency is not null");

        this.cardSecretKey = cardSecretKey;
        this.cardPaymentAgency = cardPaymentAgency;
        this.lastDigits = lastDigits;
    }

    public CardPaymentMethod(Long memberId, String cardSecretKey, String lastDigits, CardPaymentAgency cardPaymentAgency) {
        this(null, memberId, cardSecretKey, lastDigits, cardPaymentAgency);
    }
}
