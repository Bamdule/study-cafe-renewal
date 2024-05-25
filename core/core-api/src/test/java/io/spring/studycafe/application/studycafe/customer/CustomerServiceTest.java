package io.spring.studycafe.application.studycafe.customer;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void 고객생성_테스트() {
        final Long memberId = 1L;
        final Long studyCafeId = 1L;

        Customer customer = customerRepository.save(new Customer(memberId, studyCafeId));
        CustomerTicket customerTicket = customer.getCustomerTicket();


        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customerTicket.getCustomerId()).isNotNull();
        Assertions.assertThat(customerTicket.isExpired()).isTrue();
        Assertions.assertThat(customerTicket.getTicketType()).isEqualTo(customerTicket.DEFAULT_TICKET_TYPE);
        Assertions.assertThat(customerTicket.getTimeInfo()).isEqualTo(customerTicket.DEFAULT_TIME_INFO);
        Assertions.assertThat(customerTicket.getExpirationDate()).isEqualTo(customerTicket.DEFAULT_EXPIRATION_DATE);
        Assertions.assertThat(customerTicket.getCustomerId()).isEqualTo(customer.getId());
    }

    @Test
    public void 고객에게_이용권_지급_테스트() {
        Customer customer = new Customer(1L, 1L);
        Customer savedCustomer = customerRepository.save(customer);

        Ticket ticket = new Ticket("시간권", TicketType.TIME, new TimeInfo(30, 0, 0), 100000L, 30, 1L);

        savedCustomer.updateTicket(ticket);

        customerRepository.update(savedCustomer);

        Customer findCustomer = customerRepository.find(new CustomerFindQuery(1L, 1L)).get();


        Assertions.assertThat(savedCustomer.getId()).isNotNull();

    }
}
