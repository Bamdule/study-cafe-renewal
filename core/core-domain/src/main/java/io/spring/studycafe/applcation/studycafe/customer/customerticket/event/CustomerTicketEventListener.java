package io.spring.studycafe.applcation.studycafe.customer.customerticket.event;

import io.spring.studycafe.applcation.studycafe.customer.CustomerService;
import io.spring.studycafe.applcation.studycafe.customer.CustomerTicketUseCommand;
import io.spring.studycafe.applcation.studycafe.customer.CustomerTicketUpdateCommand;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CustomerTicketEventListener {
    private final CustomerService customerService;

    public CustomerTicketEventListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCustomerTicketUpdateEventHandler(CustomerTicketUpdateEvent event) {
        customerService.updateCustomerTicket(new CustomerTicketUpdateCommand(event.customerId(), event.ticketId()));
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCustomerSeatUseEndEventHandler(CustomerSeatUseEndEvent event) {
        customerService.useTicket(new CustomerTicketUseCommand(event.CustomerId(), event.timeInfo()));
    }
}
