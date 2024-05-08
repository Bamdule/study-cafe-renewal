package io.spring.studycafe.entity.studycafe.customer;

import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerCoreRepository implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;

    public CustomerCoreRepository(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Optional<Customer> find(Long memberId, Long studyCafeId) {
        return customerJpaRepository.findByMemberIdAndAndStudyCafeId(memberId, studyCafeId)
            .map(CustomerEntity::to);
    }

    @Override
    public Customer save(Customer customer) {
        return customerJpaRepository.save(CustomerEntity.of(customer)).to();
    }
}
