package io.spring.studycafe.presentation.studycafe.seat;

import io.spring.studycafe.domain.studycafe.seat.SeatCheckoutResult;

public record SeatCheckoutResponse(
    Long id,
    int number,
    String usedTime
) {

    public SeatCheckoutResponse(SeatCheckoutResult result) {
        this(result.seatId(), result.seatNumber(), result.usedTimeInfo().toString());
    }
}
