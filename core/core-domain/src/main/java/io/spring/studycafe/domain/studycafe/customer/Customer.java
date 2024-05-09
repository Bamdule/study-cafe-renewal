package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
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
        this.customerTicket = CustomerTicket.initialize();
    }

    public Customer(Long id, Long memberId, Long studyCafeId, CustomerTicket customerTicket, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.memberId = memberId;
        this.studyCafeId = studyCafeId;
        this.customerTicket = customerTicket;
    }

    public void updateTicket(Ticket ticket) {
        customerTicket.updateTicket(ticket);
    }

    public void deductTime(TimeInfo timeInfo) {
        customerTicket.deductTime(timeInfo);
    }
}