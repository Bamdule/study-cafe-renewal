package io.spring.studycafe.applcation.studycafe.customer;

import io.spring.studycafe.domain.studycafe.customer.Customer;

public record CustomerRegistrationCommand(Long memberId, Long studyCafeId) {

    public Customer toEntity() {
        return new Customer(memberId, studyCafeId);
    }
}
