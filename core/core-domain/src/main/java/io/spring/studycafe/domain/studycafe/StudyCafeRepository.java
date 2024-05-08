package io.spring.studycafe.domain.studycafe;

import java.util.List;
import java.util.Optional;

public interface StudyCafeRepository {

    List<StudyCafe> findAllByMemberId(Long memberId);

    Optional<StudyCafe> findById(Long id);

    StudyCafe save(StudyCafe studyCafe);
}
