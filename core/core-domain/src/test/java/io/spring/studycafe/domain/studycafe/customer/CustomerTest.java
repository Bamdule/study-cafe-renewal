package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class CustomerTest {

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


}