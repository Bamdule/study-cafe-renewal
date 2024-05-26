package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import io.spring.studycafe.domain.studycafe.customer.Customer;

public record PaymentCommand(Customer customer, Order order, Long paymentMethodId,
                             PaymentMethodType paymentMethodType) {
}
