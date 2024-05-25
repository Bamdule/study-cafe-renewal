package io.spring.studycafe.application.studycafe.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.common.TimeInfoCalculator;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberRepository;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import io.spring.studycafe.domain.studycafe.StudyCafe;
import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerTicketServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StudyCafeRepository studyCafeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    @Test
    public void 스터디카페_시간형티켓_차감_테스트() {

        Member member = memberRepository.save(new Member("hi11@test.com", "HI", RegistrationPlatform.KAKAO));

        StudyCafe studyCafe = studyCafeRepository.save(createStudyCafe(member));

        Customer customer = customerRepository.save(createCustomer(member, studyCafe));


        Ticket ticket1 = ticketRepository.save(createTicket(studyCafe, "시간형 티켓", TicketType.TIME, 130000L, new TimeInfo(30, 0, 0), 30));
        Ticket ticket2 = ticketRepository.save(createTicket(studyCafe, "기간형 티켓", TicketType.PERIOD, 150000L, null, 30));

        customer.updateTicket(ticket1);
        customerRepository.update(customer);

        CustomerTicket customerTicket = customer.getCustomerTicket();

        System.out.println(customerTicket.getRemainingTime());

        TimeInfo elapsedTimeInfo = TimeInfoCalculator.createElapsedTimeInfo(LocalDateTime.now().minusHours(5).minusMinutes(21), LocalDateTime.now());

        customer.getCustomerTicket()
            .deductTime(elapsedTimeInfo);

        customerRepository.update(customer);

        Customer findCustomer = customerRepository.find(new CustomerFindQuery(studyCafe.getId(), member.getId())).get();
        System.out.println(findCustomer.getCustomerTicket().getRemainingTime());
    }

    private static Ticket createTicket(StudyCafe studyCafe, String name, TicketType ticketType, Long price, TimeInfo timeInfo, int expirationDays) {
        return new Ticket(name, ticketType, timeInfo, price, expirationDays, studyCafe.getId());
    }

    private static Customer createCustomer(Member member, StudyCafe studyCafe) {
        return new Customer(member.getId(), studyCafe.getId());
    }

    private static StudyCafe createStudyCafe(Member member) {
        return new StudyCafe(
            "테스트 스터디 카페",
            "어딘가",
            "01011111111",
            member.getId()
        );
    }

}
