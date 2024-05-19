package io.spring.studycafe.applcation.studycafe.customer;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerAlreadyRegisteredException;
import io.spring.studycafe.domain.studycafe.customer.CustomerNotFoundException;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketNotFoundException;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    public CustomerService(CustomerRepository customerRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTicket(CustomerTicketUpdateCommand command) {
        Customer customer = customerRepository.findWithPessimisticLockingById(command.customerId())
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        Ticket ticket = ticketRepository.findById(command.ticketId())
            .orElseThrow(() -> new TicketNotFoundException(ExceptionCode.TICKET_NOT_FOUND));
        customer.updateTicket(ticket);

        customerRepository.update(customer);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deductTicketTime(CustomerTicketTimeDeductionCommand command) {
        Customer customer = customerRepository.findWithPessimisticLockingById(command.customerId())
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));

        customer.deductTime(command.timeInfo());

        log.info("customerId={}, deductionTime={}, remainingTimeInfo={}, customerTicketType={}",
            customer.getId(),
            command.timeInfo(),
            customer.getCustomerTicket().getTimeInfo(),
            customer.getCustomerTicket().getTicketType()
        );

        customerRepository.update(customer);
    }

    @Transactional
    public CustomerInfo register(CustomerRegistrationCommand command) {
        if (customerRepository.find(command.memberId(), command.studyCafeId()).isPresent()) {
            throw new CustomerAlreadyRegisteredException(ExceptionCode.CUSTOMER_ALREADY_REGISTERED);
        }

        Customer customer = customerRepository.save(command.toEntity());
        return CustomerInfo.of(customer);
    }

    public CustomerInfo find(Long memberId, Long studyCafeId) {
        Customer customer = customerRepository.find(memberId, studyCafeId)
            .orElseThrow(() -> new CustomerNotFoundException(ExceptionCode.CUSTOMER_NOT_FOUND));
        return CustomerInfo.of(customer);
    }
}
