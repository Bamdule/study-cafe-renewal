package io.spring.studycafe.entity.studycafe.seat;

import io.spring.studycafe.domain.studycafe.seat.SeatState;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findAllByStudyCafeId(Long studyCafeId);

    Optional<SeatEntity> findByStudyCafeIdAndCustomerId(Long studyCafeId, Long customerId);

    @Query("SELECT s FROM SeatEntity s JOIN FETCH s.customer c JOIN FETCH s.customer.customerTicket ct WHERE s.state = :state")
    List<SeatEntity> findAllByState(SeatState state);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SeatEntity s JOIN FETCH s.customer sc WHERE s.id = :id")
    Optional<SeatEntity> findWithPessimisticLockingById(Long id);
}
