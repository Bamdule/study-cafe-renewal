package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import jakarta.validation.constraints.NotNull;

public record TicketRegistrationRequest(
    @NotNull(message = "티켓 타입은 필수 값입니다.")
    TicketType type,
    TimeInfo timeInfo,
    @NotNull(message = "이용권 가격은 필수 값입니다.")
    Long price,
    Integer expirationDays
) {
}
