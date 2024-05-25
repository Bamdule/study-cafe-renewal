package io.spring.studycafe.domain.studycafe.customer.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CustomerDefaultTicket extends CustomerTicket {
    public CustomerDefaultTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo) {
        this(customerId, expirationDate, timeInfo, null, null);
    }

    public CustomerDefaultTicket(Long customerId, LocalDate expirationDate, TimeInfo timeInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(customerId, TicketType.NONE, expirationDate, timeInfo, createdAt, updatedAt);
    }

    public CustomerDefaultTicket(LocalDate expirationDate, TimeInfo timeInfo) {
        this(null, expirationDate, timeInfo, null, null);
    }

    public static CustomerDefaultTicket initialize() {
        return new CustomerDefaultTicket(
            null,
            TimeInfo.createEmpty()
        );
    }

    @Override
    public void updateTicket(Ticket ticket) {
        throw new IllegalStateException("기본 티켓은 업데이트 할 수 없습니다.");
    }

    @Override
    public boolean isTicketExpired() {
        return true;
    }

    @Override
    public void useTicket(TimeInfo usageTime) {
    }

    @Override
    public String getRemainingTime() {
        return "";
    }
}
