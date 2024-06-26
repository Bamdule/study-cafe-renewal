package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.common.TimeInfoCalculator;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerNoTicketException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Seat {
    private Long id;
    private int number;
    private Customer customer;
    private Long studyCafeId;
    private SeatState state;
    private LocalDateTime seatUsageStartDateTime;

    public TimeInfo getUsedTimeInfo() {
        if (this.isEmpty()) {
            throw new SeatEmptyException(ExceptionCode.SEAT_EMPTY);
        }

        return TimeInfoCalculator.createElapsedTimeInfo(this.seatUsageStartDateTime, LocalDateTime.now());
    }

    /**
     * 좌석 사용 종료 메소드
     * 1. 이미 빈좌석이면 예외 발생
     * 2. 사용한 시간 계산
     * 3. 빈 좌석 정보로 갱신
     */
    void empty(Customer customer) {
        if (this.customer.notEquals(customer)) {
            throw new SeatCustomerInvalidException(ExceptionCode.SEAT_CUSTOMER_INVALID);
        }

        this.customer = null;
        this.seatUsageStartDateTime = null;
        this.state = SeatState.EMPTY;

    }

    public boolean isEmpty() {
        return this.state == SeatState.EMPTY;
    }

    public boolean isUsing() {
        return !this.isEmpty();
    }

    void assignTo(Customer customer) {
        if (this.isUsing()) {
            throw new SeatAlreadyInUseException(ExceptionCode.SEAT_ALREADY_IN_USE);
        }

        if (customer.hasNotTicket()) {
            throw new CustomerNoTicketException(ExceptionCode.CUSTOMER_NO_TICKET);
        }

        this.customer = customer;
        this.seatUsageStartDateTime = LocalDateTime.now();
        this.state = SeatState.IN_USE;
    }
}
