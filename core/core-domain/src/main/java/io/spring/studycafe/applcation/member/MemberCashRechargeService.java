package io.spring.studycafe.applcation.member;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberNotFoundException;
import io.spring.studycafe.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberCashRechargeService {

    private final MemberRepository memberRepository;

    public MemberCashRechargeService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Transactional
    public void rechargeCash(Long memberId, long amount) {
        Member member = memberRepository.findByIdWithPessimisticLocking(memberId)
            .orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));

        member.rechargeCash(amount);

        memberRepository.updateCash(member);
    }
}
