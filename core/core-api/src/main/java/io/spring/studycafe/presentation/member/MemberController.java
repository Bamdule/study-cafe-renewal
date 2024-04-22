package io.spring.studycafe.presentation.member;

import io.spring.studycafe.applcation.member.MemberService;
import io.spring.studycafe.config.resolver.MemberAuthentication;
import io.spring.studycafe.config.resolver.MemberInfo;
import io.spring.studycafe.domain.member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<MemberResponse> findMember(@MemberAuthentication MemberInfo memberInfo) {

        Member member = memberService.getById(memberInfo.id());

        return ResponseEntity.ok(new MemberResponse(
            member.getId(),
            member.getEmail(),
            member.getName(),
            member.getRegistrationPlatform()
        ));
    }

}
