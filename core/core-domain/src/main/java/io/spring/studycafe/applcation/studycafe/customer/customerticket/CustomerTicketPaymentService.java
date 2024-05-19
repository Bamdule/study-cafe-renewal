package io.spring.studycafe.applcation.studycafe.customer.customerticket;

import io.spring.studycafe.applcation.event.EventPublisher;
import io.spring.studycafe.applcation.order.OrderCodeGenerator;
import io.spring.studycafe.applcation.payment.PaymentResult;
import io.spring.studycafe.applcation.payment.PaymentServiceNotFoundException;
import io.spring.studycafe.applcation.payment.PaymentServiceRouter;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.event.CustomerTicketUpdateEvent;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketNotFoundException;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerTicketPaymentService {
    private final StudyCafeRepository studyCafeRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final PaymentServiceRouter paymentServiceRouter;
    private final OrderCodeGenerator orderCodeGenerator;
    private final EventPublisher eventPublisher;

    public CustomerTicketPaymentService(StudyCafeRepository studyCafeRepository, CustomerRepository customerRepository, TicketRepository ticketRepository, PaymentServiceRouter paymentServiceRouter, OrderCodeGenerator orderCodeGenerator, EventPublisher eventPublisher) {
        this.studyCafeRepository = studyCafeRepository;
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
        this.paymentServiceRouter = paymentServiceRouter;
        this.orderCodeGenerator = orderCodeGenerator;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PaymentResult purchase(CustomerTicketPaymentCommand command) {
        validate(command);

        Customer customer = customerRepository.find(command.memberId(), command.studyCafeId())
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        Ticket ticket = ticketRepository.findById(command.ticketId())
            .orElseThrow(() -> new TicketNotFoundException(ExceptionCode.TICKET_NOT_FOUND));

        // 주문 정보 생성
        Order order = new Order(
            customer.getStudyCafeId(),
            customer.getMemberId(),
            customer.getId(),
            command.paymentMethodId(),
            ticket.getName(),
            ticket.getPrice(),
            orderCodeGenerator.generate()
        );

        // 결제 수단에 따라서 결제 서비스를 라우팅하고 결제 로직을 수행한다
        PaymentResult result = paymentServiceRouter
            .route(command.paymentMethodType())
            .orElseThrow(() -> new PaymentServiceNotFoundException(ExceptionCode.PAYMENT_SERVICE_NOT_FOUND))
            .purchase(order);

        if (result.success()) {
            eventPublisher.publish(new CustomerTicketUpdateEvent(customer.getId(), ticket.getId()));
        }

        return result;
    }

    private void validate(CustomerTicketPaymentCommand command) {
        Customer customer = customerRepository.find(command.memberId(), command.studyCafeId())
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));
        Ticket ticket = ticketRepository.findById(command.ticketId())
            .orElseThrow(() -> new TicketNotFoundException(ExceptionCode.TICKET_NOT_FOUND));

        if (customer.getStudyCafeId() == command.studyCafeId() && customer.getMemberId() == command.memberId() &&
            ticket.getStudyCafeId() == command.studyCafeId()) {
            return;
        }

        throw new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND);
    }
}
