package io.spring.studycafe.entity.studycafe.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findAllByStudyCafeId(Long studyCafeId);
}
