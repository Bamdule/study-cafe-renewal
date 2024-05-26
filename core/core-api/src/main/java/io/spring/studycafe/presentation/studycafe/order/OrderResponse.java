package io.spring.studycafe.presentation.studycafe.order;

import io.spring.studycafe.applcation.order.OrderInfo;

import java.util.List;

public record OrderResponse(
    Long id,
    String name,
    Long totalPrice,
    String orderCode,
    List<OrderItemResponse> orderItems
) {

    public static OrderResponse create(OrderInfo order) {
        return new OrderResponse(
            order.id(),
            order.name(),
            order.totalPrice(),
            order.orderCode(),
            OrderItemResponse.createList(order.orderItems())
        );
    }

    public static List<OrderResponse> createList(List<OrderInfo> orders) {
        return orders.stream().map(OrderResponse::create).toList();
    }
}
