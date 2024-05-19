package io.spring.studycafe.domain.studycafe.seat;

public enum SeatState {
    EMPTY("빈 좌석"), IN_USE("사용중인 좌석"), NOT_AVAILABLE("사용할 수 없는 좌석");

    private final String description;

    SeatState(String description) {
        this.description = description;
    }
}
