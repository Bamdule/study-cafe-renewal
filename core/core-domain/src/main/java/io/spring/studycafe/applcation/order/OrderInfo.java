package io.spring.studycafe.applcation.order;

import io.spring.studycafe.domain.order.Order;

import java.util.List;

public record OrderInfo(
    Long id,
    String name,
    Long totalPrice,
    String orderCode,
    List<OrderItemInfo> orderItems
) {

    public static OrderInfo create(Order order) {
        return new OrderInfo(
            order.getId(),
            order.getName(),
            order.getTotalPrice(),
            order.getOrderCode(),
            OrderItemInfo.createList(order.getOrderItems())
        );
    }

    public static List<OrderInfo> createList(List<Order> orders) {
        return orders.stream().map(OrderInfo::create).toList();
    }

}
