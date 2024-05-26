package io.spring.studycafe.applcation.payment.adapter;

import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentAdapterRouter {
    private final Map<PaymentMethodType, PaymentAdapter> serviceMap;

    public PaymentAdapterRouter(List<PaymentAdapter> paymentAdapters) {
        this.serviceMap = paymentAdapters
            .stream()
            .collect(Collectors.toMap(PaymentAdapter::getPaymentMethodType, Function.identity()));
    }

    public Optional<PaymentAdapter> route(PaymentMethodType paymentMethodType) {
        return Optional.ofNullable(serviceMap.get(paymentMethodType));
    }
}
