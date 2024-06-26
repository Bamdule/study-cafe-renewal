package io.spring.studycafe.domain.studycafe.customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long customerId);

    Optional<Customer> findWithPessimisticLockingById(Long id);

    Optional<Customer> find(CustomerFindQuery query);

    Customer save(Customer customer);

    void update(Customer customer);
}
