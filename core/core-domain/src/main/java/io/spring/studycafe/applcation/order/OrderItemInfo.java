package io.spring.studycafe.applcation.order;

import io.spring.studycafe.domain.order.orderitem.OrderItem;
import io.spring.studycafe.domain.order.orderitem.OrderItemType;

import java.util.List;

public record OrderItemInfo(
    Long id,
    Long itemId,
    String name,
    Long price,
    OrderItemType type,
    Integer quantity
) {

    public static OrderItemInfo create(OrderItem orderItem) {
        return new OrderItemInfo(
            orderItem.getId(),
            orderItem.getItemId(),
            orderItem.getName(),
            orderItem.getPrice(),
            orderItem.getType(),
            orderItem.getQuantity()
        );
    }

    public static List<OrderItemInfo> createList(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItemInfo::create).toList();
    }
}
