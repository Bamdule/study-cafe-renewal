package io.spring.studycafe.entity.studycafe.order;

import io.spring.studycafe.domain.order.orderitem.OrderItem;
import io.spring.studycafe.domain.order.orderitem.OrderItemType;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "orders_item")
@Entity
public class OrderItemEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Long price;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OrderItemType type;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    protected OrderItemEntity() {
    }

    public OrderItemEntity(OrderEntity order, OrderItem orderItem) {
        this.order = order;
        this.itemId = orderItem.getItemId();
        this.name = orderItem.getName();
        this.price = orderItem.getPrice();
        this.type = orderItem.getType();
        this.quantity = orderItem.getQuantity();
    }

    public OrderItem toModel() {
        return new OrderItem(
            this.id,
            this.order.getId(),
            this.itemId,
            this.name,
            this.price,
            this.type,
            this.quantity
        );
    }


    public static List<OrderItem> convert(List<OrderItemEntity> entities) {
        return entities.stream().map(OrderItemEntity::toModel).toList();
    }
}
