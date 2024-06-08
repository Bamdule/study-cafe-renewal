package io.spring.studycafe.applcation.studycafe.seat;

import io.spring.studycafe.applcation.event.EventPublisher;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.event.CustomerSeatUseEndEvent;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.seat.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final CustomerRepository customerRepository;
    private final EventPublisher eventPublisher;

    public SeatService(SeatRepository seatRepository, CustomerRepository customerRepository, EventPublisher eventPublisher) {
        this.seatRepository = seatRepository;
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional(readOnly = true)
    public List<SeatInfo> findAll(Long studyCafeId) {
        return seatRepository.findAll(studyCafeId).stream().map(SeatInfo::of).toList();
    }

    @Transactional
    public SeatInfo useSeat(Long seatId, Long memberId) {
        // 좌석 조회
        Seat seat = seatRepository.findWithPessimisticLockingById(seatId)
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_NOT_FOUND));

        // 고객 정보 조회
        Customer customer = customerRepository.find(new CustomerFindQuery(seat.getStudyCafeId(), memberId))
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        // 다른 좌석을 사용하고 있는지 체크
        validateOtherSeatAssigned(customer);

        seat.assignTo(customer);

        seatRepository.update(seat);
        return SeatInfo.of(seat);
    }

    @Transactional
    public SeatInfo leaveSeat(Long studyCafeId, Long seatId, Long memberId) {

        // 내 좌석 정보 조회
        Seat seat = seatRepository.findWithPessimisticLockingById(seatId)
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_INVALID));

        // 고객 정보 조회
        Customer customer = customerRepository.find(new CustomerFindQuery(studyCafeId, memberId))
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));


        if (seat.getCustomer().getId() != customer.getId()) {
            throw new SeatInvalidException(ExceptionCode.SEAT_INVALID);
        }

        // 빈 좌석이면 예외 발생
        if (seat.isEmpty()) {
            throw new SeatEmptyException(ExceptionCode.SEAT_EMPTY);
        }

        eventPublisher.publish(new CustomerSeatUseEndEvent(customer.getId(), seat.getUsedTimeInfo()));

        seat.leave();
        seatRepository.update(seat);

        return SeatInfo.of(seat);
    }

    private void validateOtherSeatAssigned(Customer customer) {
        seatRepository.findByStudyCafeIdAndCustomerId(customer.getStudyCafeId(), customer.getId())
            .ifPresent(s -> {
                throw new SeatOnlyOneUsableException(ExceptionCode.SEAT_ONLY_ONE_USABLE);
            });
    }


}
