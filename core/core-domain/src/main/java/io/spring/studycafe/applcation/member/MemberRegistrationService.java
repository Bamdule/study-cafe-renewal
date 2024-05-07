package io.spring.studycafe.applcation.member;

import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberRepository;
import io.spring.studycafe.domain.member.cash.MemberCash;
import io.spring.studycafe.domain.member.cash.MemberCashRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberRegistrationService {

    private final MemberRepository memberRepository;
    private final MemberCashRepository memberCashRepository;

    public MemberRegistrationService(MemberRepository memberRepository, MemberCashRepository memberCashRepository) {
        this.memberRepository = memberRepository;
        this.memberCashRepository = memberCashRepository;
    }

    @Transactional
    public Member register(MemberRegistrationCommand command) {
        Member member = memberRepository.save(command.toEntity());
//        memberCashRepository.save(new MemberCash(member.getId()));

        //todo:: 회원가입 알림 전송
        return member;
    }
}
