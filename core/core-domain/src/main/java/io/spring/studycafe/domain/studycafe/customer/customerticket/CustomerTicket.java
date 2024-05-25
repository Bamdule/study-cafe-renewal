package io.spring.studycafe.domain.studycafe.customer.customerticket;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public abstract class CustomerTicket extends BaseModel {

    public static final TicketType DEFAULT_TICKET_TYPE = TicketType.NONE;
    public static final TimeInfo DEFAULT_TIME_INFO = new TimeInfo(0, 0, 0);
    public static final LocalDate DEFAULT_EXPIRATION_DATE = null;

    protected Long customerId;

    protected TicketType ticketType;

    protected LocalDate expirationDate;

    protected TimeInfo timeInfo;

    public CustomerTicket(Long customerId, TicketType ticketType, LocalDate expirationDate, TimeInfo timeInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.expirationDate = expirationDate;
        this.timeInfo = timeInfo;
    }

    public abstract void updateTicket(Ticket ticket);

    public abstract boolean isTicketExpired();

    public abstract void useTicket(TimeInfo usageTime);

    public abstract String getRemainingTime();

    protected boolean isExpired() {
        return this.expirationDate.isBefore(LocalDate.now());
    }

    protected void updateExpirationDate(int expirationDays){
        if (this.isExpired()) {
            this.expirationDate = LocalDate.now().plusDays(expirationDays);
        } else {
            this.expirationDate = this.expirationDate.plusDays(expirationDays);
        }
    }
}
