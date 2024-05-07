package io.spring.studycafe.entity.member;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM MemberEntity m JOIN FETCH m.memberCash mc WHERE m.id = :id")
    Optional<MemberEntity> findWithPessimisticLockingById(Long id);

}
