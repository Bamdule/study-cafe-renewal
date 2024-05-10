package io.spring.studycafe.entity.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.spring.studycafe.entity.paymentmethod.PaymentMethodEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@DiscriminatorValue("CARD")
@Table(name = "card_payment_method")
@Entity
public class CardPaymentMethodEntity extends PaymentMethodEntity {

    @Column(name = "card_secret_key", nullable = false)
    private String cardSecretKey;

    @Column(name = "last_digits", nullable = false)
    private String lastDigits;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_payment_agency", nullable = false)
    private CardPaymentAgency cardPaymentAgency;

    public CardPaymentMethodEntity(Long id, Long memberId, String cardSecretKey, String lastDigits, CardPaymentAgency cardPaymentAgency) {
        super(id, memberId);
        this.cardSecretKey = cardSecretKey;
        this.lastDigits = lastDigits;
        this.cardPaymentAgency = cardPaymentAgency;
    }

    protected CardPaymentMethodEntity() {
        super();
    }

    public CardPaymentMethod toModel() {
        return new CardPaymentMethod(
            getId(),
            getMemberId(),
            cardSecretKey,
            lastDigits,
            cardPaymentAgency
        );
    }

    public static CardPaymentMethodEntity of(CardPaymentMethod cardPaymentMethod) {
        return new CardPaymentMethodEntity(
            cardPaymentMethod.getId(),
            cardPaymentMethod.getMemberId(),
            cardPaymentMethod.getCardSecretKey(),
            cardPaymentMethod.getLastDigits(),
            cardPaymentMethod.getCardPaymentAgency()
        );
    }
}
