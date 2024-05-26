package io.spring.studycafe.entity.studycafe.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {


    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.orderItems oi WHERE o.customerId = :customerId")
    List<OrderEntity> findAllByCustomerId(Long customerId);
}
