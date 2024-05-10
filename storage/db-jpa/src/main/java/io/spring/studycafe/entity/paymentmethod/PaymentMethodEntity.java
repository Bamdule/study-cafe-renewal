package io.spring.studycafe.entity.paymentmethod;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Table(name = "payment_method")
@Entity
public abstract class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;


    protected PaymentMethodEntity() {
    }

    protected PaymentMethodEntity(Long id, Long memberId) {
        this.id = id;
        this.memberId = memberId;
    }
}
