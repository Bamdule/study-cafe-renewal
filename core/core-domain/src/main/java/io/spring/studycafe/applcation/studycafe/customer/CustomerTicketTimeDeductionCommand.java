package io.spring.studycafe.applcation.studycafe.customer;

import io.spring.studycafe.domain.common.TimeInfo;

public record CustomerTicketTimeDeductionCommand(Long customerId, TimeInfo timeInfo) {
}
