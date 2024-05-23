package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class CustomerTest {

    @Test
    void 고객_생성_테스트() {
        Customer customer = createNoTicketCustomer(1L, 1L, 1L);

        Assertions.assertThat(customer.getId()).isNotNull();
        Assertions.assertThat(customer.getMemberId()).isNotNull();
        Assertions.assertThat(customer.getStudyCafeId()).isNotNull();
        Assertions.assertThat(customer.hasNotTicket()).isTrue();
    }

    @Test
    void 고객_이용권_갱신_테스트() {
        Customer customer = new Customer(1L, 1L);

        Ticket ticket = new Ticket(
            1L,
            "테스트 시간 티켓",
            TicketType.TIME,
            new TimeInfo(30, 0, 0),
            120000,
            30,
            1L,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        customer.updateTicket(ticket);

        Assertions.assertThat(customer.getMemberId()).isNotNull();
        Assertions.assertThat(customer.getStudyCafeId()).isNotNull();
        Assertions.assertThat(customer.hasTicket()).isTrue();
    }

    @Test
    public void 고객_티켓_시간_차감_테스트() {
        CustomerTicket customerTicket = new CustomerTicket(
            1L, TicketType.TIME, new TimeInfo(1, 10, 10), LocalDate.now().plusDays(1)
        );
        Customer customer = new Customer(
            1L,
            1L,
            1L,
            customerTicket,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        customer.getCustomerTicket()
            .deductTime(new TimeInfo(1, 5, 1));


        Assertions.assertThat(customer.getCustomerTicket().getTimeInfo()).isEqualTo(new TimeInfo(0, 5, 9));
    }


    private static Customer createCustomer(Long customerId, Long memberId, Long studyCafeId) {

        CustomerTicket customerTicket = new CustomerTicket(
            customerId,
            TicketType.TIME,
            new TimeInfo(1, 1, 1),
            LocalDate.now().plusDays(30),
            LocalDateTime.now(),
            LocalDateTime.now()
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

        CustomerTicket customerTicket = new CustomerTicket(
            customerId,
            TicketType.NONE,
            new TimeInfo(0, 0, 0),
            null,
            LocalDateTime.now(),
            LocalDateTime.now()
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

}