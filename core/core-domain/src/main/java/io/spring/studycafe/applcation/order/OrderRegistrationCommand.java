package io.spring.studycafe.applcation.order;

import io.spring.studycafe.domain.order.orderitem.OrderItem;
import io.spring.studycafe.domain.studycafe.customer.Customer;

import java.util.List;

public record OrderRegistrationCommand(
    Customer customer,
    Long paymentMethodId,
    String name,
    Long totalPrice,
    List<OrderItem> orderItems
) {
}
