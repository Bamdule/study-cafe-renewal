package io.spring.studycafe.entity.studycafe.order;

import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Order> findAll(Long customerId) {
        return orderJpaRepository.findAllByCustomerId(customerId)
            .stream().map(OrderEntity::toModel).toList();
    }
}
