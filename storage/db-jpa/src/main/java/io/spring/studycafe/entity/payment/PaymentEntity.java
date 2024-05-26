package io.spring.studycafe.entity.payment;

import io.spring.studycafe.domain.payment.Payment;
import io.spring.studycafe.domain.payment.PaymentResultType;
import io.spring.studycafe.entity.common.BaseModelEntity;
import jakarta.persistence.*;

@Table(name = "payment")
@Entity
public class PaymentEntity extends BaseModelEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "study_cafe_id", nullable = false)
    private Long studyCafeId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "customer_id", nullable = true)
    private Long customerId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "payment_method_id", nullable = false)
    private Long paymentMethodId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "order_code", nullable = false, unique = true)
    private String orderCode;

    @Column(name = "result_code", nullable = false)
    private String resultCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "result_type", nullable = false)
    private PaymentResultType resultType;

    protected PaymentEntity() {
    }

    public PaymentEntity(Long studyCafeId, Long memberId, Long customerId, Long orderId, Long paymentMethodId, String name, Long totalPrice, String message, String orderCode, String resultCode, PaymentResultType resultType) {
        this.studyCafeId = studyCafeId;
        this.memberId = memberId;
        this.customerId = customerId;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
        this.name = name;
        this.totalPrice = totalPrice;
        this.message = message;
        this.orderCode = orderCode;
        this.resultCode = resultCode;
        this.resultType = resultType;
    }

    public static PaymentEntity create(Payment payment) {
        return new PaymentEntity(
            payment.getStudyCafeId(),
            payment.getMemberId(),
            payment.getCustomerId(),
            payment.getOrderId(),
            payment.getPaymentMethodId(),
            payment.getName(),
            payment.getTotalPrice(),
            payment.getMessage(),
            payment.getOrderCode(),
            payment.getResultCode(),
            payment.getResultType()
        );
    }

    public Payment toModel() {
        return new Payment(
            this.id,
            this.studyCafeId,
            this.memberId,
            this.customerId,
            this.orderId,
            this.paymentMethodId,
            this.name,
            this.totalPrice,
            this.message,
            this.orderCode,
            this.resultCode,
            this.resultType,
            this.getCreatedAt(),
            this.getUpdatedAt()
        );
    }
}
