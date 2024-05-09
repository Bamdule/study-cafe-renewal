package io.spring.studycafe.applcation.studycafe.customerticket;

import io.spring.studycafe.applcation.payment.PaymentResult;
import io.spring.studycafe.applcation.payment.PaymentServiceNotFoundException;
import io.spring.studycafe.applcation.payment.PaymentServiceRouter;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.paymentmethod.PaymentMethodType;
import io.spring.studycafe.domain.studycafe.StudyCafe;
import io.spring.studycafe.domain.studycafe.StudyCafeNotFoundException;
import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketNotFoundException;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerTicketPurchaseService {
    private final StudyCafeRepository studyCafeRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final PaymentServiceRouter paymentServiceRouter;

    public CustomerTicketPurchaseService(StudyCafeRepository studyCafeRepository, CustomerRepository customerRepository, TicketRepository ticketRepository, PaymentServiceRouter paymentServiceRouter) {
        this.studyCafeRepository = studyCafeRepository;
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
        this.paymentServiceRouter = paymentServiceRouter;
    }

    public PaymentResult purchase(Long studyCafeId, Long memberId, Long ticketId, PaymentMethodType paymentMethodType, Long paymentMethodId) {
        // 스터디 카페 조회
        StudyCafe studyCafe = studyCafeRepository.findById(studyCafeId)
            .orElseThrow(() -> new StudyCafeNotFoundException(ExceptionCode.STUDY_CAFE_NOT_FOUND));

        // 고객 정보 조회 (고객 정보가 없다면 생성)
        Customer customer = customerRepository.find(memberId, studyCafeId)
            .orElseGet(() -> customerRepository.save(new Customer(memberId, studyCafe.getId())));

        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new TicketNotFoundException(ExceptionCode.TICKET_NOT_FOUND));

        // 주문 정보 생성
        Order order = new Order(
            studyCafeId,
            customer.getId(),
            paymentMethodId,
            ticket.getName(),
            ticket.getPrice(),
            UUID.randomUUID().toString()
        );

        // 결제 수단에 따라서 결제 서비스를 라우팅하고 결제 로직을 수행한다
        PaymentResult result = paymentServiceRouter
            .route(paymentMethodType)
            .orElseThrow(() -> new PaymentServiceNotFoundException(ExceptionCode.PAYMENT_SERVICE_NOT_FOUND))
            .purchase(order);

        if (result.success()) {
            customer.updateTicket(ticket);
            customerRepository.update(customer);
        }

        // 고객 이용권 정보 조회
        // 고객 이용권 정보 갱신
        // 고객 이용권 정보 반환

        return result;
    }
}
