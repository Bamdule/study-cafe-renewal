package io.spring.studycafe.domain.order;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.order.orderitem.OrderItem;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order extends BaseModel {
    private Long id;
    private Long studyCafeId;
    private Long memberId;
    private Long customerId;
    private Long paymentMethodId;
    private String name;
    private Long totalPrice;
    private String orderCode;
    private List<OrderItem> orderItems;

    public Order(Long id, Long studyCafeId, Long memberId, Long customerId, Long paymentMethodId, String name, Long totalPrice, String orderCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.studyCafeId = studyCafeId;
        this.memberId = memberId;
        this.customerId = customerId;
        this.paymentMethodId = paymentMethodId;
        this.name = name;
        this.totalPrice = totalPrice;
        this.orderCode = orderCode;
    }

    public Order(Customer customer, Long paymentMethodId, String name, Long totalPrice, String orderCode) {
        this(null, customer.getStudyCafeId(), customer.getMemberId(), customer.getId(), paymentMethodId, name, totalPrice, orderCode, null, null);
    }
}
