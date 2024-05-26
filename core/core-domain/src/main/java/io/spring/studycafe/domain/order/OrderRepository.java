package io.spring.studycafe.domain.order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findAll(Long customerId);
}
