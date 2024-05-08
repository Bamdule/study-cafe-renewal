package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;

public record TicketResponse(
    Long id,
    String name,
    TicketType type,
    TimeInfo timeInfo,
    long price,
    int expirationDays
) {
}
