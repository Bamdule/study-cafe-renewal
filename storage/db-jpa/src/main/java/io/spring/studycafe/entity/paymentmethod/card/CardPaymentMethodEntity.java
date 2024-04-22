package io.spring.studycafe.entity.paymentmethod.card;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "card_payment_method")
@Entity
public class CardPaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id", nullable = false, unique = true)
    private Long memberId;

    @Column(name = "card_secret_key", nullable = false)
    private String cardSecretKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_payment_agency", nullable = false)
    private CardPaymentAgency cardPaymentAgency;

    public CardPaymentMethodEntity(Long id, Long memberId, String cardSecretKey, CardPaymentAgency cardPaymentAgency) {
        this.id = id;
        this.memberId = memberId;
        this.cardSecretKey = cardSecretKey;
        this.cardPaymentAgency = cardPaymentAgency;
    }

    protected CardPaymentMethodEntity() {
    }

    public CardPaymentMethod toModel() {
        return new CardPaymentMethod(
            id,
            memberId,
            cardSecretKey,
            cardPaymentAgency
        );
    }

    public static CardPaymentMethodEntity of(CardPaymentMethod cardPaymentMethod) {
        return new CardPaymentMethodEntity(
            cardPaymentMethod.getId(),
            cardPaymentMethod.getMemberId(),
            cardPaymentMethod.getCardSecretKey(),
            cardPaymentMethod.getCardPaymentAgency()
        );
    }
}
