package io.spring.studycafe.applcation.order;

import io.spring.studycafe.domain.studycafe.customer.Customer;

public record OrderRegistrationCommand(
    Customer customer,
    Long paymentMethodId,
    String name,
    Long totalPrice
) {
}
