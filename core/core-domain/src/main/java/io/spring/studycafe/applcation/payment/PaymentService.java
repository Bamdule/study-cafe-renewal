package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;

public interface PaymentService {


    PaymentResult purchase(Order order);


    PaymentMethodType getPaymentMethodType();
}
