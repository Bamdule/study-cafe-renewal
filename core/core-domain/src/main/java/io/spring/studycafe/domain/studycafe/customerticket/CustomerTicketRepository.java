package io.spring.studycafe.domain.studycafe.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;

import java.util.Optional;

public interface CustomerTicketRepository {
    Optional<CustomerTicket> findByCustomerId(Long customerId);

    CustomerTicket save(CustomerTicket customerTicket);

    CustomerTicket deduction(CustomerTicket customerTicket, TimeInfo usageTime);

    void delete(Long id);
}
