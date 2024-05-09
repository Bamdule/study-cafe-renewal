package io.spring.studycafe.applcation.payment.pay;

import io.spring.studycafe.applcation.payment.PaymentResult;
import io.spring.studycafe.applcation.payment.PaymentService;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import org.springframework.stereotype.Service;

@Service
public class PayPaymentService implements PaymentService {
    @Override
    public PaymentResult purchase(Order order) {
        return null;
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.PAY;
    }
}
