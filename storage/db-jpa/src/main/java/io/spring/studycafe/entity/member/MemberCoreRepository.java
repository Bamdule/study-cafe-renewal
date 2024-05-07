package io.spring.studycafe.entity.member;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberNotFoundException;
import io.spring.studycafe.domain.member.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberCoreRepository implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    public MemberCoreRepository(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id)
            .map(MemberEntity::toModel);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
            .map(MemberEntity::toModel);
    }

    @Override
    public Optional<Member> findByIdWithPessimisticLocking(Long id) {
        return memberJpaRepository.findWithPessimisticLockingById(id)
            .map(MemberEntity::toModel);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.of(member))
            .toModel();
    }

    @Override
    public Member updateCash(Member member) {
        MemberEntity memberEntity = memberJpaRepository.findById(member.getId())
            .orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));

        memberEntity.updateMemberCash(member.getMemberCash().getCash());

        return memberEntity.toModel();
    }
}
