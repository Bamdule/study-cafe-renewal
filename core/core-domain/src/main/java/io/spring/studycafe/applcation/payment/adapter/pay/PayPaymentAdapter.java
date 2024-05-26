package io.spring.studycafe.applcation.payment.adapter.pay;

import io.spring.studycafe.applcation.payment.adapter.PaymentResult;
import io.spring.studycafe.applcation.payment.adapter.PaymentAdapter;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import org.springframework.stereotype.Service;

@Service
public class PayPaymentAdapter implements PaymentAdapter {
    @Override
    public PaymentResult purchase(Order order) {
        return null;
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.PAY;
    }
}
