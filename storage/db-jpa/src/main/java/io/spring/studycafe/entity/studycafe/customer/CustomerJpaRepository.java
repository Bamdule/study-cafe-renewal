package io.spring.studycafe.entity.studycafe.customer;

import io.spring.studycafe.entity.member.MemberEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c JOIN FETCH c.customerTicket ct WHERE c.memberId = :memberId and c.studyCafeId = :studyCafeId")
    Optional<CustomerEntity> findByMemberIdAndAndStudyCafeId(Long memberId, Long studyCafeId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CustomerEntity c JOIN FETCH c.customerTicket ct WHERE c.id = :id")
    Optional<CustomerEntity> findWithPessimisticLockingById(Long id);
}
