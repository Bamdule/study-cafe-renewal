package io.spring.studycafe.applcation.studycafe.customer;

public record CustomerTicketUpdateCommand(Long customerId, Long ticketId) {
}
