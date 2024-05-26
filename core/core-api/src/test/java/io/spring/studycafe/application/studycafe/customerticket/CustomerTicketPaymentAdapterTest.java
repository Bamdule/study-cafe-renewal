package io.spring.studycafe.application.studycafe.customerticket;

import io.spring.studycafe.applcation.payment.ticket.TicketPaymentService;
import io.spring.studycafe.applcation.payment.PaymentInfo;
import io.spring.studycafe.applcation.paymentmethod.card.CardInfo;
import io.spring.studycafe.applcation.paymentmethod.card.CardRegisterCommand;
import io.spring.studycafe.applcation.paymentmethod.card.CardRegisterService;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketPaymentCommand;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberRepository;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import io.spring.studycafe.domain.studycafe.StudyCafe;
import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerTicketPaymentAdapterTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StudyCafeRepository studyCafeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketPaymentService ticketPaymentService;

    @Autowired
    private CardRegisterService cardRegisterService;

    @Test
    public void 고객_좌석_이용권_구매_테스트() {
        Member member = memberRepository.save(new Member("hi12@test.com", "HI", RegistrationPlatform.KAKAO));
        StudyCafe studyCafe = studyCafeRepository.save(createStudyCafe(member));
        Customer customer = customerRepository.save(createCustomer(member, studyCafe));
        Ticket ticket1 = ticketRepository.save(createTicket(studyCafe, "시간형 티켓", TicketType.TIME, 130000L, new TimeInfo(30, 0, 0), 30));
        Ticket ticket2 = ticketRepository.save(createTicket(studyCafe, "기간형 티켓", TicketType.PERIOD, 150000L, null, 30));
        CardInfo cardInfo = cardRegisterService.register(new CardRegisterCommand(member.getId(), "1234123412341234", "1234", "12", "1234", "123123", CardPaymentAgency.NICEPAY));

        CustomerTicketPaymentCommand command = new CustomerTicketPaymentCommand(studyCafe.getId(), member.getId(), ticket1.getId(), PaymentMethodType.CARD, cardInfo.id());

        PaymentInfo paymentInfo = ticketPaymentService.purchase(command);

        System.out.println(paymentInfo);

        Customer findCustomer = customerRepository.find(new CustomerFindQuery(studyCafe.getId(), member.getId())).get();

        Assertions.assertThat(findCustomer.getId()).isNotNull();
        Assertions.assertThat(findCustomer.getMemberId()).isEqualTo(member.getId());
        Assertions.assertThat(findCustomer.getCustomerTicket()).isNotNull();
        Assertions.assertThat(findCustomer.getCustomerTicket().getTicketType()).isEqualTo(ticket1.getType());
        Assertions.assertThat(findCustomer.getCustomerTicket().isTicketExpired()).isEqualTo(false);
        Assertions.assertThat(findCustomer.getCustomerTicket().getTimeInfo()).isEqualTo(ticket1.getTimeInfo());
        Assertions.assertThat(findCustomer.getCustomerTicket().getExpirationDate()).isEqualTo(LocalDate.now().plusDays(ticket1.getExpirationDays()));
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
