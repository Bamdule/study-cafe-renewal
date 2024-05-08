package io.spring.studycafe.domain.studycafe.customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> find(Long memberId, Long studyCafeId);

    Customer save(Customer customer);
}
