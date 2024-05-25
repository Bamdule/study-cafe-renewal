package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerDefaultTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Customer extends BaseModel {
    private Long id;
    private Long memberId;
    private Long studyCafeId;
    private CustomerTicket customerTicket;

    public Customer(Long memberId, Long studyCafeId) {
        this.memberId = memberId;
        this.studyCafeId = studyCafeId;
        this.customerTicket = CustomerDefaultTicket.initialize();
    }

    public Customer(Long id, Long memberId, Long studyCafeId, CustomerTicket customerTicket, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.memberId = memberId;
        this.studyCafeId = studyCafeId;
        this.customerTicket = customerTicket;
    }

    public void useTicket(TimeInfo usageTime) {
        customerTicket.useTicket(usageTime);
    }

    public void updateCustomerTicket(CustomerTicket customerTicket) {
        this.customerTicket = customerTicket;
    }

    public void updateTicket(Ticket ticket) {
        this.customerTicket.updateTicket(ticket);
    }

    public boolean hasTicket() {
        return !this.hasNotTicket();
    }

    public boolean hasNotTicket() {
        return customerTicket.isTicketExpired();
    }

    public TicketType getTicketType() {
        return customerTicket.getTicketType();
    }
}
