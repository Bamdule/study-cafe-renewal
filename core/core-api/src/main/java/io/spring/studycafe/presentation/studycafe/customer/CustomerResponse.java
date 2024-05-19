package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.studycafe.customer.CustomerInfo;

public record CustomerResponse(
    Long id,
    Long memberId,
    CustomerTicketResponse customerTicket
) {

    public static CustomerResponse of(CustomerInfo customerInfo) {
        if (customerInfo == null) {
            return null;
        }

        return new CustomerResponse(
            customerInfo.id(),
            customerInfo.memberId(),
            CustomerTicketResponse.of(customerInfo.customerTicketInfo())
        );
    }
}
