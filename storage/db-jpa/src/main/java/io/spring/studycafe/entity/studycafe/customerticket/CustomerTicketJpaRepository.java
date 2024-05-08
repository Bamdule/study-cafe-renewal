package io.spring.studycafe.entity.studycafe.customerticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerTicketJpaRepository extends JpaRepository<CustomerTicketEntity, Long> {

    Optional<CustomerTicketEntity> findByCustomerId(Long customerId);
}
