package io.spring.studycafe.entity.studycafe;

import io.spring.studycafe.domain.studycafe.StudyCafe;
import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudyCafeCoreRepository implements StudyCafeRepository {

    private final StudyCafeJpaRepository studyCafeJpaRepository;

    public StudyCafeCoreRepository(StudyCafeJpaRepository studyCafeJpaRepository) {
        this.studyCafeJpaRepository = studyCafeJpaRepository;
    }

    @Override
    public List<StudyCafe> findAllByMemberId(Long memberId) {
        return studyCafeJpaRepository.findAllByMemberId(memberId).stream().map(StudyCafeEntity::to).toList();
    }

    @Override
    public Optional<StudyCafe> findById(Long id) {
        return studyCafeJpaRepository.findById(id).map(StudyCafeEntity::to);
    }

    @Override
    public StudyCafe save(StudyCafe studyCafe) {
        return studyCafeJpaRepository.save(StudyCafeEntity.of(studyCafe)).to();
    }
}
