package io.spring.studycafe.applcation.studycafe.ticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;

public record TicketRegistrationCommand(
    String name,
    TicketType type,
    TimeInfo timeInfo,
    long price,
    int expirationDays,
    Long studyCafeId
) {

    public Ticket toEntity() {
        return new Ticket(
            this.name,
            this.type,
            this.timeInfo,
            this.price,
            this.expirationDays,
            this.studyCafeId
        );
    }
}
