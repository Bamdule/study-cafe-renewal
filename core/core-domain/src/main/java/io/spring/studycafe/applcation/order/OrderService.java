package io.spring.studycafe.applcation.order;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.order.OrderRepository;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCodeGenerator orderCodeGenerator;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, OrderCodeGenerator orderCodeGenerator, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderCodeGenerator = orderCodeGenerator;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order register(OrderRegistrationCommand command) {
        Order order = new Order(
            command.customer(),
            command.paymentMethodId(),
            command.name(),
            command.totalPrice(),
            orderCodeGenerator.generate(),
            command.orderItems()
        );

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderInfo> findAll(Long studyCafeId, Long memberId) {
        Customer customer = customerRepository.find(new CustomerFindQuery(studyCafeId, memberId))
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        return OrderInfo.createList(orderRepository.findAll(customer.getId()));
    }
}
