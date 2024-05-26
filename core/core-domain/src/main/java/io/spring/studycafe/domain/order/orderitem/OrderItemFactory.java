package io.spring.studycafe.domain.order.orderitem;

public interface OrderItemFactory<T> {

    OrderItem create(T item);

    OrderItemType getOrderItemType();
}
