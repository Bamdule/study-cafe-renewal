package io.spring.studycafe.entity.studycafe.order;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.order.orderitem.OrderItem;
import io.spring.studycafe.entity.common.BaseModelEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Table(name = "orders")
@Entity
public class OrderEntity extends BaseModelEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "study_cafe_id", nullable = false)
    private Long studyCafeId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "payment_method_id", nullable = false)
    private Long paymentMethodId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "total_price", nullable = false)
    private Long totalPrice;
    @Column(name = "order_code", nullable = false, unique = true)
    private String orderCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    protected OrderEntity() {
    }

    public OrderEntity(Long studyCafeId, Long memberId, Long customerId, Long paymentMethodId, String name, Long totalPrice, String orderCode, List<OrderItem> orderItems) {
        this.studyCafeId = studyCafeId;
        this.memberId = memberId;
        this.customerId = customerId;
        this.paymentMethodId = paymentMethodId;
        this.name = name;
        this.totalPrice = totalPrice;
        this.orderCode = orderCode;
        this.orderItems = createOrderItemEntity(orderItems);
    }

    private List<OrderItemEntity> createOrderItemEntity(List<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> new OrderItemEntity(this, orderItem)).toList();
    }

    public static OrderEntity create(Order order) {
        return new OrderEntity(
            order.getStudyCafeId(),
            order.getMemberId(),
            order.getCustomerId(),
            order.getPaymentMethodId(),
            order.getName(),
            order.getTotalPrice(),
            order.getOrderCode(),
            order.getOrderItems()
        );
    }

    public Order toModel() {
        return new Order(
            this.id,
            this.studyCafeId,
            this.memberId,
            this.customerId,
            this.paymentMethodId,
            this.name,
            this.totalPrice,
            this.orderCode,
            OrderItemEntity.convert(this.orderItems),
            this.getCreatedAt(),
            this.getUpdatedAt()
        );
    }
}
