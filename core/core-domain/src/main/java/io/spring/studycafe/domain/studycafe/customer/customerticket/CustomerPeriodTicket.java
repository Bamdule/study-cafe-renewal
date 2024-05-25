package io.spring.studycafe.domain.studycafe.customer.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CustomerPeriodTicket extends CustomerTicket {

    public CustomerPeriodTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo) {
        this(customerId, expirationDate, timeInfo, null, null);
    }

    public CustomerPeriodTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(customerId, TicketType.PERIOD, expirationDate, timeInfo, createdAt, updatedAt);
    }

    public static CustomerTicket create(Long customerId, Ticket ticket) {
        return new CustomerPeriodTicket(customerId, LocalDate.now().plusDays(ticket.getExpirationDays()), ticket.getTimeInfo());
    }

    @Override
    public void updateTicket(Ticket ticket) {
        super.updateExpirationDate(ticket.getExpirationDays());
    }

    @Override
    public boolean isTicketExpired() {
        return this.isExpired();
    }

    @Override
    public void useTicket(TimeInfo usageTime) {
    }

    @Override
    public String getRemainingTime() {
        return Duration.between(LocalDate.now(), expirationDate).toDays() + "일";
    }
}
