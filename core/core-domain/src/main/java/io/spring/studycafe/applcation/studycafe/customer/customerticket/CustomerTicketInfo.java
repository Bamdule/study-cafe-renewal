package io.spring.studycafe.applcation.studycafe.customer.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;

import java.time.LocalDate;

public record CustomerTicketInfo(
    TicketType ticketType,
    TimeInfo timeInfo,
    LocalDate expirationDate
) {
    public static CustomerTicketInfo of(CustomerTicket customerTicket) {
        return new CustomerTicketInfo(
            customerTicket.getTicketType(),
            customerTicket.getTimeInfo(),
            customerTicket.getExpirationDate()
        );
    }
}
