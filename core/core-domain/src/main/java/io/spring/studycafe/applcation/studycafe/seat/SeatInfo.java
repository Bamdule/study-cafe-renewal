package io.spring.studycafe.applcation.studycafe.seat;

import io.spring.studycafe.applcation.studycafe.customer.CustomerInfo;
import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatState;

import java.time.LocalDateTime;

public record SeatInfo(
    Long id,
    int number,
    CustomerInfo customerInfo,
    Long studyCafeId,
    SeatState state,
    LocalDateTime seatUsageStartDateTime
) {

    public static SeatInfo of(Seat seat) {
        if (seat.isUsing()) {
            return new SeatInfo(
                seat.getId(),
                seat.getNumber(),
                CustomerInfo.of(seat.getCustomer()),
                seat.getStudyCafeId(),
                seat.getState(),
                seat.getSeatUsageStartDateTime()
            );
        }

        return new SeatInfo(
            seat.getId(),
            seat.getNumber(),
            null,
            seat.getStudyCafeId(),
            seat.getState(),
            null
        );
    }
}
