package io.spring.studycafe.applcation.studycafe.seat;

import io.spring.studycafe.applcation.event.EventPublisher;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.event.CustomerSeatUseEndEvent;
import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatCheckoutResult;
import io.spring.studycafe.domain.studycafe.seat.SeatManager;
import io.spring.studycafe.domain.studycafe.seat.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final EventPublisher eventPublisher;
    private final SeatManager seatManager;

    public SeatService(SeatRepository seatRepository, EventPublisher eventPublisher, SeatManager seatManager) {
        this.seatRepository = seatRepository;
        this.eventPublisher = eventPublisher;
        this.seatManager = seatManager;
    }

    @Transactional(readOnly = true)
    public List<SeatInfo> findAll(Long studyCafeId) {
        return seatRepository.findAll(studyCafeId).stream().map(SeatInfo::of).toList();
    }

    @Transactional
    public SeatInfo checkIn(Long seatId, Long memberId) {
        Seat seat = seatManager.checkIn(seatId, memberId);
        return SeatInfo.of(seat);
    }

    @Transactional
    public SeatCheckoutResult checkOut(Long seatId, Long memberId) {
        SeatCheckoutResult seatCheckOutResult = seatManager.checkout(seatId, memberId);
        eventPublisher.publish(
            new CustomerSeatUseEndEvent(
                seatCheckOutResult.customerId(),
                seatCheckOutResult.usedTimeInfo()
            ))
        ;

        return seatCheckOutResult;
    }

}
