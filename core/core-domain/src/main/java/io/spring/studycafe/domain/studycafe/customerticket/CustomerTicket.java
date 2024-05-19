package io.spring.studycafe.domain.studycafe.customerticket;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.common.TimeInfoCalculator;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CustomerTicket extends BaseModel {

    public static final TicketType DEFAULT_TICKET_TYPE = TicketType.NONE;
    public static final TimeInfo DEFAULT_TIME_INFO = new TimeInfo(0, 0, 0);
    public static final LocalDate DEFAULT_EXPIRATION_DATE = null;

    private Long customerId;

    private TicketType ticketType;

    private TimeInfo timeInfo;

    private LocalDate expirationDate;

    public CustomerTicket(Long customerId, TicketType ticketType, TimeInfo timeInfo, LocalDate expirationDate) {
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.timeInfo = timeInfo;
        this.expirationDate = expirationDate;
    }

    public CustomerTicket(Long customerId, TicketType ticketType, TimeInfo timeInfo, LocalDate expirationDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.timeInfo = timeInfo;
        this.expirationDate = expirationDate;
    }

    public static CustomerTicket initialize() {
        return new CustomerTicket(
            null,
            DEFAULT_TICKET_TYPE,
            DEFAULT_TIME_INFO,
            DEFAULT_EXPIRATION_DATE
        );
    }

    public void empty() {
        this.ticketType = DEFAULT_TICKET_TYPE;
        this.timeInfo = DEFAULT_TIME_INFO;
        this.expirationDate = DEFAULT_EXPIRATION_DATE;
    }

    public String getRemainingTime() {
        if (ticketType == TicketType.TIME) {
            return timeInfo.toString();
        }

        return Duration.between(LocalDate.now(), expirationDate).toDays() + "일";
    }


    public void updateTicket(Ticket ticket) {
        if (this.ticketType == ticket.getType()) {
            switch (ticketType) {
                case TIME -> addTimeInfo(ticket.getTimeInfo());
                case PERIOD -> addExpirationDate(ticket.getExpirationDays());
            }
        } else {
            this.ticketType = ticket.getType();
            this.timeInfo = ticket.getTimeInfo();
            this.expirationDate = LocalDate.now().plusDays(ticket.getExpirationDays());
        }
    }

    public boolean isExpired() {
        switch (ticketType) {
            case TIME:
                return this.timeInfo.isEmpty();
            case PERIOD:
                return this.expirationDate == null || this.expirationDate.isBefore(LocalDate.now());
        }

        return true;
    }

    private void addExpirationDate(int expirationDays) {
        if (this.isExpired()) {
            this.expirationDate = LocalDate.now().plusDays(expirationDays);

        } else {
            this.expirationDate = this.expirationDate.plusDays(expirationDays);
        }
    }

    private void addTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = TimeInfoCalculator.add(this.timeInfo, timeInfo);
    }

    public void deductTime(TimeInfo timeInfo) {
        if (this.ticketType == TicketType.TIME) {
            this.timeInfo = TimeInfoCalculator.subtract(this.timeInfo, timeInfo);

            if (this.timeInfo.isEmpty()) {
                this.empty();
            }
        }
    }
}
