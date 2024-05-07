package io.spring.studycafe.applcation.member;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberNotFoundException;
import io.spring.studycafe.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberAggregateSearchService {

    private final MemberRepository memberRepository;

    public MemberAggregateSearchService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    public Member getById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
