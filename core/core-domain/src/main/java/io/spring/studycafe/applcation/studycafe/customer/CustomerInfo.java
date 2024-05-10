package io.spring.studycafe.applcation.studycafe.customer;

import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketInfo;
import io.spring.studycafe.domain.studycafe.customer.Customer;

public record CustomerInfo(
    Long id,
    Long memberId,
    Long studyCafeId,
    CustomerTicketInfo customerTicketInfo
) {

    public static CustomerInfo of(Customer customer) {
        return new CustomerInfo(
            customer.getId(),
            customer.getMemberId(),
            customer.getStudyCafeId(),
            CustomerTicketInfo.of(customer.getCustomerTicket())
        );
    }
}
