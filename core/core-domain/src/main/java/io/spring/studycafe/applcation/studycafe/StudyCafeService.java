package io.spring.studycafe.applcation.studycafe;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberNotFoundException;
import io.spring.studycafe.domain.member.MemberRepository;
import io.spring.studycafe.domain.studycafe.StudyCafe;
import io.spring.studycafe.domain.studycafe.StudyCafeNotFoundException;
import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyCafeService {
    private final StudyCafeRepository studyCafeRepository;
    private final MemberRepository memberRepository;

    public StudyCafeService(StudyCafeRepository studyCafeRepository, MemberRepository memberRepository) {
        this.studyCafeRepository = studyCafeRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public StudyCafeInfo register(StudyCafeRegistrationCommand command) {

        Member member = memberRepository.findById(command.memberId())
            .orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));

        StudyCafe studyCafe = studyCafeRepository.save(command.toEntity(member));
        return StudyCafeInfo.of(studyCafe);
    }

    public List<StudyCafeInfo> findAllByMemberId(Long memberId) {
        return studyCafeRepository.findAllByMemberId(memberId)
            .stream()
            .map(StudyCafeInfo::of)
            .toList();

    }

    public StudyCafeInfo findById(Long id) {
        StudyCafe studyCafe = studyCafeRepository.findById(id)
            .orElseThrow(() -> new StudyCafeNotFoundException(ExceptionCode.STUDY_CAFE_NOT_FOUND));

        return StudyCafeInfo.of(studyCafe);
    }
}
