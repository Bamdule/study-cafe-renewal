package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.applcation.payment.adapter.PaymentAdapterRouter;
import io.spring.studycafe.applcation.payment.adapter.PaymentApiAdapterNotFoundException;
import io.spring.studycafe.applcation.payment.adapter.PaymentResult;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.payment.Payment;
import io.spring.studycafe.domain.payment.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentAdapterRouter paymentAdapterRouter;

    public PaymentService(PaymentRepository paymentRepository, PaymentAdapterRouter paymentAdapterRouter) {
        this.paymentRepository = paymentRepository;
        this.paymentAdapterRouter = paymentAdapterRouter;
    }

    @Transactional
    public Payment purchase(PaymentCommand command) {
        final Order order = command.order();

        PaymentResult result = paymentAdapterRouter
            .route(command.paymentMethodType())
            .orElseThrow(() -> new PaymentApiAdapterNotFoundException(ExceptionCode.PAYMENT_SERVICE_NOT_FOUND))
            .purchase(order);

        return paymentRepository.save(createPayment(command, result));
    }

    private Payment createPayment(PaymentCommand command, PaymentResult result) {
        return new Payment(command.customer(), command.order(), command.paymentMethodId(), result.message(), result.resultCode(), result.resultType());
    }


}
