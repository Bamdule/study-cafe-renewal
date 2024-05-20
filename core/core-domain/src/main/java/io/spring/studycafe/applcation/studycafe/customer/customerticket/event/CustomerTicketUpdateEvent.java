package io.spring.studycafe.applcation.studycafe.customer.customerticket.event;

public record CustomerTicketUpdateEvent(
    Long customerId,
    Long ticketId
) {
}
