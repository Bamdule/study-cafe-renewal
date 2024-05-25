package io.spring.studycafe.entity.studycafe.seat;

import io.spring.studycafe.domain.studycafe.seat.SeatState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findAllByStudyCafeId(Long studyCafeId);

    Optional<SeatEntity> findByStudyCafeIdAndCustomerId(Long studyCafeId, Long customerId);

    @Query("SELECT s FROM SeatEntity s JOIN FETCH s.customer mc WHERE s.state = :state")
    List<SeatEntity> findAllByState(SeatState state);
}
