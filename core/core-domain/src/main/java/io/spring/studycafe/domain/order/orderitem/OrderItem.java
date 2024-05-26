package io.spring.studycafe.domain.order.orderitem;

import lombok.Getter;

@Getter
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long itemId;
    private String name;
    private Long price;
    private OrderItemType type;
    private Integer quantity;

    public OrderItem(Long itemId, String name, Long price, OrderItemType type, Integer quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public OrderItem(Long id, Long orderId, Long itemId, String name, Long price, OrderItemType type, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }
}
