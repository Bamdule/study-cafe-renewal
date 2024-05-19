package io.spring.studycafe.entity.studycafe.seat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findAllByStudyCafeId(Long studyCafeId);

    Optional<SeatEntity> findByStudyCafeIdAndCustomerId(Long studyCafeId, Long customerId);
}
