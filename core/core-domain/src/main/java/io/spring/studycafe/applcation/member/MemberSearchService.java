package io.spring.studycafe.applcation.member;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberNotFoundException;
import io.spring.studycafe.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberSearchService {

    private final MemberRepository memberRepository;

    public MemberSearchService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    public MemberInfo getById(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));
        return MemberInfo.of(member);
    }
}
