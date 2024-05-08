package io.spring.studycafe.domain.studycafe.customerticket;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class CustomerTicket extends BaseModel {
    private Long id;

    private Long customerId;

    private TicketType ticketType;

    private TimeInfo timeInfo;

    private LocalDateTime expirationDate;

    public CustomerTicket(Long customerId, TicketType ticketType, TimeInfo timeInfo, LocalDateTime expirationDate) {
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.timeInfo = timeInfo;
        this.expirationDate = expirationDate;
    }

    public CustomerTicket(Long id, Long customerId, TicketType ticketType, TimeInfo timeInfo, LocalDateTime expirationDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.timeInfo = timeInfo;
        this.expirationDate = expirationDate;
    }

    public String getRemainingTime() {
        if (ticketType == TicketType.TIME) {
            return timeInfo.toString();
        }

        return Duration.between(LocalDateTime.now(), expirationDate).toDays() + "일";
    }
}
