package io.spring.studycafe.entity.studycafe;

import io.spring.studycafe.domain.studycafe.StudyCafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyCafeJpaRepository extends JpaRepository<StudyCafeEntity, Long> {
    List<StudyCafeEntity> findAllByMemberId(Long memberId);

    Optional<StudyCafeEntity> findByMemberId(Long memberId);
}
