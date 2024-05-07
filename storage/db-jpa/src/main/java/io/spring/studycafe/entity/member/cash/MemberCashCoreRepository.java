package io.spring.studycafe.entity.member.cash;

import io.spring.studycafe.domain.member.cash.MemberCash;
import io.spring.studycafe.domain.member.cash.MemberCashRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberCashCoreRepository implements MemberCashRepository {

    private final MemberCashJpaRepository memberCashJpaRepository;

    public MemberCashCoreRepository(MemberCashJpaRepository memberCashJpaRepository) {
        this.memberCashJpaRepository = memberCashJpaRepository;
    }

    @Override
    public MemberCash save(MemberCash memberCash) {
        return null;
//        return memberCashJpaRepository.save(MemberCashEntity.of(memberCash))
//            .toModel();
    }

    @Override
    public Optional<MemberCash> findByMemberId(Long memberId) {
        return null;
//        return memberCashJpaRepository.findById(memberId)
//            .map(MemberCashEntity::toModel);
    }
}
