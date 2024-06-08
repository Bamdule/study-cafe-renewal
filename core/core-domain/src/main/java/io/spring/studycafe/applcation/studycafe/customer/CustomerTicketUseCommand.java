package io.spring.studycafe.applcation.studycafe.customer;

import io.spring.studycafe.domain.common.TimeInfo;

public record CustomerTicketUseCommand(Long customerId, TimeInfo timeInfo) {
}
