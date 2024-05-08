package io.spring.studycafe.entity.studycafe.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByMemberIdAndAndStudyCafeId(Long memberId, Long studyCafeId);
}
