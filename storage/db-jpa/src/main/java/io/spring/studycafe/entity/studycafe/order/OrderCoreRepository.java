package io.spring.studycafe.entity.studycafe.order;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCoreRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderCoreRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(OrderEntity.create(order)).toModel();
    }
}
