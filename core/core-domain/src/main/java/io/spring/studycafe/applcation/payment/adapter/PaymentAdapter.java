package io.spring.studycafe.applcation.payment.adapter;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;

public interface PaymentAdapter {


    PaymentResult purchase(Order order);


    PaymentMethodType getPaymentMethodType();
}
