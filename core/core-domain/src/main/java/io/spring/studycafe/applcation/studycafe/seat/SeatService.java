package io.spring.studycafe.applcation.studycafe.seat;

import io.spring.studycafe.applcation.event.EventPublisher;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.event.CustomerTicketTimeDeductionEvent;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatEmptyException;
import io.spring.studycafe.domain.studycafe.seat.SeatNotFoundException;
import io.spring.studycafe.domain.studycafe.seat.SeatRepository;
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

    public List<SeatInfo> findAll(Long studyCafeId) {
        return seatRepository.findAll(studyCafeId).stream().map(SeatInfo::new).toList();
    }

    @Transactional
    public SeatInfo useSeat(Long seatId, Long memberId) {
        Seat seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_NOT_FOUND));

        Customer customer = customerRepository.find(memberId, seat.getStudyCafeId())
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        seat.use(customer);

        seatRepository.update(seat);

        return new SeatInfo(seat);
    }

    @Transactional
    public SeatInfo leaveSeat(Long studyCafeId, Long memberId) {

        Customer customer = customerRepository.find(memberId, studyCafeId)
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        Seat seat = seatRepository.findByStudyCafeIdAndCustomerId(studyCafeId, customer.getId())
            .orElseThrow(() -> new SeatNotFoundException(ExceptionCode.SEAT_NOT_FOUND));

        // 빈 좌석이면 예외 발생
        if (seat.isEmpty()) {
            throw new SeatEmptyException(ExceptionCode.SEAT_EMPTY);
        }

        switch (seat.getCustomer().getTicketType()) {
            case TIME ->
                eventPublisher.publish(new CustomerTicketTimeDeductionEvent(customer.getId(), seat.getUsedTimeInfo()));
        }

        seat.leave();
        seatRepository.update(seat);

        return new SeatInfo(seat);
    }


}
