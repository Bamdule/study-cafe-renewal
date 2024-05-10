package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketInfo;
import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;

import java.time.LocalDate;

public record CustomerTicketResponse(
    TicketType ticketType,
    TimeInfo timeInfo,
    LocalDate expirationDate
) {

    public static CustomerTicketResponse of(CustomerTicketInfo customerTicketInfo) {
        return new CustomerTicketResponse(
            customerTicketInfo.ticketType(),
            customerTicketInfo.timeInfo(),
            customerTicketInfo.expirationDate()
        );
    }
}
