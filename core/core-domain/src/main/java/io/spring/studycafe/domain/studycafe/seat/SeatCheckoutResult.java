package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.TimeInfo;

public record SeatCheckoutResult(Long seatId, Long customerId, TimeInfo usedTimeInfo, int seatNumber) {
}
