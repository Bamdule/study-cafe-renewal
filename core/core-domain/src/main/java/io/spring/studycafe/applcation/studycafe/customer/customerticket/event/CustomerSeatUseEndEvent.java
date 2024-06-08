package io.spring.studycafe.applcation.studycafe.customer.customerticket.event;

import io.spring.studycafe.domain.common.TimeInfo;

public record CustomerSeatUseEndEvent(
    Long CustomerId,
    TimeInfo timeInfo
) {
}
