package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.studycafe.customer.CustomerInfo;

public record CustomerResponse(
    Long id,
    Long memberId,
    Long studyCafeId,
    CustomerTicketResponse customerTicket
) {

    public static CustomerResponse of(CustomerInfo customerInfo) {
        return new CustomerResponse(
            customerInfo.id(),
            customerInfo.memberId(),
            customerInfo.studyCafeId(),
            CustomerTicketResponse.of(customerInfo.customerTicketInfo())
        );
    }
}
