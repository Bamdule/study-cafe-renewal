package io.spring.studycafe.applcation.order;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCodeGenerator orderCodeGenerator;

    public OrderService(OrderRepository orderRepository, OrderCodeGenerator orderCodeGenerator) {
        this.orderRepository = orderRepository;
        this.orderCodeGenerator = orderCodeGenerator;
    }

    @Transactional
    public Order register(OrderRegistrationCommand command) {
        Order order = new Order(
            command.customer(),
            command.paymentMethodId(),
            command.name(),
            command.totalPrice(),
            orderCodeGenerator.generate()
        );

        return orderRepository.save(order);
    }
}
