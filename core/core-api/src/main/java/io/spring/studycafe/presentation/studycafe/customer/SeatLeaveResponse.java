package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.studycafe.seat.SeatInfo;
import io.spring.studycafe.domain.studycafe.seat.SeatState;

import java.time.LocalDateTime;

public record SeatLeaveResponse(
    Long id,
    int number,
    CustomerResponse customer,
    SeatState state,
    LocalDateTime seatUsageStartDateTime
) {

    public SeatLeaveResponse(SeatInfo seatInfo) {
        this(seatInfo.id(), seatInfo.number(), CustomerResponse.of(seatInfo.customerInfo()), seatInfo.state(), seatInfo.seatUsageStartDateTime());
    }
}
