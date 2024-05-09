package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentServiceRouter {
    private final Map<PaymentMethodType, PaymentService> serviceMap;

    public PaymentServiceRouter(List<PaymentService> paymentServices) {
        this.serviceMap = paymentServices
            .stream()
            .collect(Collectors.toMap(PaymentService::getPaymentMethodType, Function.identity()));
    }

    public Optional<PaymentService> route(PaymentMethodType paymentMethodType) {
        return Optional.ofNullable(serviceMap.get(paymentMethodType));
    }
}
