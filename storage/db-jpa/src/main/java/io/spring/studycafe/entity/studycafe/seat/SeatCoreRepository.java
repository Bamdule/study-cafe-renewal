package io.spring.studycafe.entity.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatNotFoundException;
import io.spring.studycafe.domain.studycafe.seat.SeatRepository;
import io.spring.studycafe.domain.studycafe.seat.SeatState;
import io.spring.studycafe.entity.studycafe.customer.CustomerEntity;
import io.spring.studycafe.entity.studycafe.customer.CustomerJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class SeatCoreRepository implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;

    public SeatCoreRepository(SeatJpaRepository seatJpaRepository, CustomerJpaRepository customerJpaRepository) {
        this.seatJpaRepository = seatJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public List<Seat> findAll(Long studyCafeId) {
        return seatJpaRepository.findAllByStudyCafeId(studyCafeId)
            .stream().map(SeatEntity::toModel).toList();
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatJpaRepository.findById(id)
            .map(SeatEntity::toModel);
    }

    @Override
    public Optional<Seat> findByStudyCafeIdAndCustomerId(Long studyCafeId, Long customerId) {
        return seatJpaRepository.findByStudyCafeIdAndCustomerId(studyCafeId, customerId)
            .map(SeatEntity::toModel);
    }

    @Transactional
    @Override
    public void update(Seat seat) {

        SeatEntity seatEntity = seatJpaRepository.findById(seat.getId())
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_NOT_FOUND));

        if (seat.isEmpty()) {
            seatEntity.updateEmpty();
        } else {

            if (seat.getCustomer() == null || seat.getCustomer().getId() == null) {
                throw new IllegalArgumentException("customerId must not be null");
            }

            CustomerEntity customerEntity = customerJpaRepository.findById(seat.getCustomer().getId())
                .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

            seatEntity.updateInUse(seat, customerEntity);
        }
    }

    @Override
    public List<Seat> findAllInUse() {
        return seatJpaRepository.findAllByState(SeatState.IN_USE)
            .stream().map(SeatEntity::toModel).toList();
    }
}
