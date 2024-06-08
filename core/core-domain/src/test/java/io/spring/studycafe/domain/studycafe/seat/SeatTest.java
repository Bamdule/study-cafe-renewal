package io.spring.studycafe.domain.studycafe.seat;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerNoTicketException;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerDefaultTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTimeTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SeatTest {

    @Test
    void 빈_좌석_생성_테스트() {
        final Long studyCafeId = 1L;
        final int seatNumber = 1;

        Seat seat = new Seat(
            1L,
            seatNumber,
            null,
            studyCafeId,
            SeatState.EMPTY,
            null
        );

        assertThat(seat.getId()).isNotNull();
        assertThat(seat.getCustomer()).isNull();
        assertThat(seat.getSeatUsageStartDateTime()).isNull();
        assertThat(seat.getState()).isEqualTo(SeatState.EMPTY);
        assertThat(seat.isUsing()).isFalse();
        assertThat(seat.getStudyCafeId()).isEqualTo(studyCafeId);
        assertThat(seat.getNumber()).isEqualTo(seatNumber);
        assertThat(seat.isEmpty()).isTrue();
    }

    @Test
    void 좌석_사용_성공_테스트() {
        final Long studyCafeId = 1L;
        final Long memberId = 1L;
        final Long customerId = 1L;
        final int seatNumber = 1;

        Customer customer = createCustomer(customerId, memberId, studyCafeId);

        Seat seat = new Seat(
            1L,
            seatNumber,
            null,
            studyCafeId,
            SeatState.EMPTY,
            null
        );

        seat.assignTo(customer);

        assertThat(seat.getId()).isNotNull();
        assertThat(seat.getCustomer()).isNotNull();
        assertThat(seat.getSeatUsageStartDateTime()).isNotNull();
        assertThat(seat.getState()).isEqualTo(SeatState.IN_USE);
        assertThat(seat.isUsing()).isTrue();
        assertThat(seat.getStudyCafeId()).isEqualTo(studyCafeId);
        assertThat(seat.getNumber()).isEqualTo(seatNumber);
        assertThat(seat.isEmpty()).isFalse();
    }

    @Test
    void 좌석사용시_이미_사용중인_좌석_예외_테스트() {
        final Long studyCafeId = 1L;
        final int seatNumber = 1;

        Customer customer = createCustomer(1L, 1L, studyCafeId);
        Customer customer2 = createCustomer(2L, 2L, studyCafeId);

        Seat seat = new Seat(
            1L,
            seatNumber,
            null,
            studyCafeId,
            SeatState.EMPTY,
            null
        );

        seat.assignTo(customer);
        Assertions.assertThatThrownBy(() -> seat.assignTo(customer2))
            .isInstanceOf(SeatAlreadyInUseException.class)
            .hasMessage(ExceptionCode.SEAT_ALREADY_IN_USE.getMessage());
    }

    @Test
    void 좌석사용시_고객_티켓_없음_예외_테스트() {
        final Long studyCafeId = 1L;
        final int seatNumber = 1;

        Customer customer = createNoTicketCustomer(1L, 1L, studyCafeId);

        Seat seat = new Seat(
            1L,
            seatNumber,
            null,
            studyCafeId,
            SeatState.EMPTY,
            null
        );

        Assertions.assertThatThrownBy(() -> seat.assignTo(customer))
            .isInstanceOf(CustomerNoTicketException.class)
            .hasMessage(ExceptionCode.CUSTOMER_NO_TICKET.getMessage());
    }

    @Test
    void 좌석반납_테스트() {
        final Long studyCafeId = 1L;
        final int seatNumber = 1;

        Customer customer = createCustomer(1L, 1L, studyCafeId);

        Seat seat = new Seat(
            1L,
            seatNumber,
            null,
            studyCafeId,
            SeatState.EMPTY,
            null
        );

        seat.assignTo(customer);
        TimeInfo usedTimeInfo = seat.getUsedTimeInfo();

        seat.leave();
        Assertions.assertThat(usedTimeInfo).isNotNull();
        Assertions.assertThat(seat.isEmpty()).isTrue();
        Assertions.assertThat(seat.getSeatUsageStartDateTime()).isNull();
    }

    @Test
    void 좌석사용시간계산_테스트() {
        final Long studyCafeId = 1L;
        final int seatNumber = 1;

        Customer customer = createCustomer(1L, 1L, studyCafeId);

        final int usingMinutes = 5;

        Seat seat = new Seat(
            1L,
            seatNumber,
            customer,
            studyCafeId,
            SeatState.IN_USE,
            LocalDateTime.now().minusMinutes(usingMinutes)
        );

        TimeInfo usedTimeInfo = seat.getUsedTimeInfo();
        Assertions.assertThat(usedTimeInfo).isEqualTo(new TimeInfo(0, 0, usingMinutes));
    }

    private static Customer createCustomer(Long customerId, Long memberId, Long studyCafeId) {

        CustomerTicket customerTicket = new CustomerTimeTicket(
            customerId,
            LocalDate.now().plusDays(30),
            new TimeInfo(1, 1, 1)
        );

        return new Customer(
            customerId,
            memberId,
            studyCafeId,
            customerTicket,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    private static Customer createNoTicketCustomer(Long customerId, Long memberId, Long studyCafeId) {

        CustomerTicket customerTicket = CustomerDefaultTicket.initialize();

        return new Customer(
            customerId,
            memberId,
            studyCafeId,
            customerTicket,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

}