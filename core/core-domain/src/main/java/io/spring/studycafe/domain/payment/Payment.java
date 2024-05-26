package io.spring.studycafe.domain.payment;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Payment extends BaseModel {
    private Long id;
    private Long studyCafeId;
    private Long memberId;
    private Long customerId;
    private Long orderId;
    private Long paymentMethodId;
    private String name;
    private Long totalPrice;
    private String message;
    private String orderCode;
    private String resultCode;
    private PaymentResultType resultType;


    public Payment(Long id, Long studyCafeId, Long memberId, Long customerId, Long orderId, Long paymentMethodId, String name, Long totalPrice, String message, String orderCode, String resultCode, PaymentResultType resultType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
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

    public Payment(Long studyCafeId, Long memberId, Long customerId, Long orderId, Long paymentMethodId, String name, Long totalPrice, String message, String orderCode, String resultCode, PaymentResultType resultType) {
        this(null, studyCafeId, memberId, customerId, orderId, paymentMethodId, name, totalPrice, message, orderCode, resultCode, resultType, null, null);
    }

    public Payment(Customer customer, Order order, Long paymentMethodId, String message, String resultCode, PaymentResultType resultType) {
        this(null, customer.getStudyCafeId(), customer.getMemberId(), customer.getId(), order.getId(), paymentMethodId, order.getName(), order.getTotalPrice(), message, order.getOrderCode(), resultCode, resultType, null, null);
    }


    public boolean success() {
        return this.resultType == PaymentResultType.PAYMENT_SUCCESS;
    }
}
