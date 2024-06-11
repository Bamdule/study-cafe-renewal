package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SeatManager {
    private final SeatRepository seatRepository;
    private final CustomerRepository customerRepository;

    public SeatManager(SeatRepository seatRepository, CustomerRepository customerRepository) {
        this.seatRepository = seatRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Seat checkIn(Long seatId, Long memberId) {
        // 좌석 조회
        Seat seat = seatRepository.findWithPessimisticLockingById(seatId)
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_NOT_FOUND));

        // 고객 정보 조회
        Customer customer = customerRepository.find(new CustomerFindQuery(seat.getStudyCafeId(), memberId))
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        validateOtherSeatAssigned(customer);

        seat.assignTo(customer);
        seatRepository.update(seat);
        return seat;
    }

    @Transactional
    public SeatCheckoutResult checkout(Long seatId, Long memberId) {

        // 내 좌석 정보 조회
        Seat seat = seatRepository.findWithPessimisticLockingById(seatId)
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_INVALID));

        // 고객 정보 조회
        Customer customer = customerRepository.find(new CustomerFindQuery(seat.getStudyCafeId(), memberId))
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        if (seat.isEmpty()) {
            throw new SeatEmptyException(ExceptionCode.SEAT_EMPTY);
        }

        TimeInfo usedTimeInfo = seat.getUsedTimeInfo();

        seat.empty(customer);
        seatRepository.update(seat);
        return new SeatCheckoutResult(seatId, customer.getId(), usedTimeInfo, seat.getNumber());
    }

    private void validateOtherSeatAssigned(Customer customer) {
        seatRepository.findByStudyCafeIdAndCustomerId(customer.getStudyCafeId(), customer.getId())
            .ifPresent(s -> {
                throw new SeatOnlyOneUsableException(ExceptionCode.SEAT_ONLY_ONE_USABLE);
            });
    }
}
