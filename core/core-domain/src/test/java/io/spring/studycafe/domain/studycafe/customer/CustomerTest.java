package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.common.TimeInfoCalculator;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerDefaultTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTimeTicket;
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

        customer.updateCustomerTicket(CustomerTimeTicket.create(customer.getId(), ticket));

        Assertions.assertThat(customer.getMemberId()).isNotNull();
        Assertions.assertThat(customer.getStudyCafeId()).isNotNull();
        Assertions.assertThat(customer.hasTicket()).isTrue();
    }

    @Test
    void 고객_이용권_시간티켓_추가_업데이트_테스트() {
        TimeInfo timeInfo = new TimeInfo(1, 1, 1);
        CustomerTicket customerTimeTicket = createCustomerTimeTicket(1L, LocalDate.now().plusDays(30), timeInfo);
        Customer customer = createCustomer(1L, 1L, 1L, customerTimeTicket);

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

        Assertions.assertThat(customer.getCustomerTicket().getTimeInfo()).isEqualTo(TimeInfoCalculator.add(timeInfo, ticket.getTimeInfo()));
        Assertions.assertThat(customer.getMemberId()).isNotNull();
        Assertions.assertThat(customer.getStudyCafeId()).isNotNull();
        Assertions.assertThat(customer.hasTicket()).isTrue();
    }

    @Test
    public void 고객_티켓_시간_차감_테스트() {
        CustomerTicket customerTicket = new CustomerTimeTicket(
            1L, LocalDate.now().plusDays(1), new TimeInfo(1, 10, 10)
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
            .useTicket(new TimeInfo(1, 5, 1));


        Assertions.assertThat(customer.getCustomerTicket().getTimeInfo()).isEqualTo(new TimeInfo(0, 5, 9));
    }


    private static Customer createCustomer(Long customerId, Long memberId, Long studyCafeId, CustomerTicket customerTicket) {

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

    public CustomerTicket createCustomerTimeTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo) {
        return new CustomerTimeTicket(
            customerId,
            expirationDate,
            timeInfo
        );
    }

}