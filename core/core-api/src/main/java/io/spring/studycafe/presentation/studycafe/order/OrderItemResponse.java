package io.spring.studycafe.presentation.studycafe.order;

import io.spring.studycafe.applcation.order.OrderItemInfo;
import io.spring.studycafe.domain.order.orderitem.OrderItemType;

import java.util.List;

public record OrderItemResponse(
    Long id,
    Long itemId,
    String name,
    Long price,
    OrderItemType type,
    Integer quantity
) {

    public static OrderItemResponse create(OrderItemInfo orderItem) {
        return new OrderItemResponse(
            orderItem.id(),
            orderItem.itemId(),
            orderItem.name(),
            orderItem.price(),
            orderItem.type(),
            orderItem.quantity()
        );
    }

    public static List<OrderItemResponse> createList(List<OrderItemInfo> orderItems) {
        return orderItems.stream().map(OrderItemResponse::create).toList();
    }
}
