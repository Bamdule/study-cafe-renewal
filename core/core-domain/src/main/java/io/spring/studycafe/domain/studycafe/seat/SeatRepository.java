package io.spring.studycafe.domain.studycafe.seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {

    List<Seat> findAll(Long studyCafeId);

    Optional<Seat> findById(Long id);

    Optional<Seat> findWithPessimisticLockingById(Long id);

    Optional<Seat> findByStudyCafeIdAndCustomerId(Long studyCafeId, Long customerId);

    void update(Seat seat);

    List<Seat> findAllInUse();
}
