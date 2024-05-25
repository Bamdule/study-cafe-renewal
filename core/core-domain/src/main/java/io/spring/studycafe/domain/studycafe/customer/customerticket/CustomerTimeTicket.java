package io.spring.studycafe.domain.studycafe.customer.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.common.TimeInfoCalculator;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CustomerTimeTicket extends CustomerTicket {

    public CustomerTimeTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo) {
        this(customerId, expirationDate, timeInfo, null, null);
    }

    public CustomerTimeTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(customerId, TicketType.TIME, expirationDate, timeInfo, createdAt, updatedAt);
    }

    public static CustomerTicket create(Long customerId, Ticket ticket) {
        return new CustomerTimeTicket(customerId, LocalDate.now().plusDays(ticket.getExpirationDays()), ticket.getTimeInfo());
    }

    @Override
    public void updateTicket(Ticket ticket) {
        this.timeInfo = TimeInfoCalculator.add(this.timeInfo, ticket.getTimeInfo());
        super.updateExpirationDate(ticket.getExpirationDays());
    }

    public void deductTime(TimeInfo timeInfo) {
        this.timeInfo = TimeInfoCalculator.subtract(this.timeInfo, timeInfo);
    }

    public void deductAllTime() {
        this.deductTime(this.timeInfo);
    }

    @Override
    public boolean isTicketExpired() {
        return this.timeInfo.isEmpty() || this.isExpired();
    }

    @Override
    public void useTicket(TimeInfo usageTime) {
        this.deductTime(usageTime);
    }

    @Override
    public String getRemainingTime() {
        return timeInfo.toString();
    }
}
