package io.spring.studycafe.applcation.studycafe.ticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;

public record TicketInfo(
    Long id,
    String name,
    TicketType type,
    TimeInfo timeInfo,
    long price,
    int expirationDays
) {

    public static TicketInfo create(Ticket ticket) {
        return new TicketInfo(
            ticket.getId(),
            ticket.getName(),
            ticket.getType(),
            ticket.getTimeInfo(),
            ticket.getPrice(),
            ticket.getExpirationDays()
        );

    }
}
