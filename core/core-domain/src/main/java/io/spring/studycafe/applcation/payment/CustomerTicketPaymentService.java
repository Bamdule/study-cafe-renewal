package io.spring.studycafe.applcation.payment;

import io.spring.studycafe.applcation.event.EventPublisher;
import io.spring.studycafe.applcation.order.OrderRegistrationCommand;
import io.spring.studycafe.applcation.order.OrderService;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketPaymentCommand;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.event.CustomerTicketUpdateEvent;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.order.Order;
import io.spring.studycafe.domain.payment.Payment;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerFindQuery;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketNotFoundException;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerTicketPaymentService {
    private final OrderService orderService;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;
    private final EventPublisher eventPublisher;

    public CustomerTicketPaymentService(OrderService orderService, CustomerRepository customerRepository, TicketRepository ticketRepository, PaymentService paymentService, EventPublisher eventPublisher) {
        this.orderService = orderService;
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
        this.paymentService = paymentService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PaymentInfo purchase(CustomerTicketPaymentCommand command) {
        validate(command);

        Customer customer = customerRepository.find(new CustomerFindQuery(command.studyCafeId(), command.memberId()))
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        Ticket ticket = ticketRepository.findById(command.ticketId())
            .orElseThrow(() -> new TicketNotFoundException(ExceptionCode.TICKET_NOT_FOUND));

        // 주문 신청
        Order order = orderService.register(new OrderRegistrationCommand(
            customer,
            command.paymentMethodId(),
            ticket.getName(),
            ticket.getPrice()
        ));

        // 결제 수단에 따라서 결제 서비스를 라우팅하고 결제 로직을 수행한다
        Payment payment = paymentService.purchase(new PaymentCommand(customer, order, command.paymentMethodId(), command.paymentMethodType()));

        if (payment.success()) {
            eventPublisher.publish(new CustomerTicketUpdateEvent(customer.getId(), ticket.getId()));
        }

        return PaymentInfo.create(payment);
    }

    private void validate(CustomerTicketPaymentCommand command) {
        Customer customer = customerRepository.find(new CustomerFindQuery(command.studyCafeId(), command.memberId()))
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
