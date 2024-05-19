package io.spring.studycafe.applcation.studycafe.customer.customerticket.event;

import io.spring.studycafe.domain.common.TimeInfo;

public record CustomerTicketTimeDeductionEvent(
    Long CustomerId,
    TimeInfo timeInfo
) {
}
