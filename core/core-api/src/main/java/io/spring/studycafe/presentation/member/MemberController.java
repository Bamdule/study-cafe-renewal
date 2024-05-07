package io.spring.studycafe.presentation.member;

import io.spring.studycafe.applcation.member.MemberSearchService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.spring.studycafe.domain.member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/members")
@RestController
public class MemberController {

    private final MemberSearchService memberSearchService;

    public MemberController(MemberSearchService memberSearchService) {
        this.memberSearchService = memberSearchService;
    }


    @GetMapping
    public ResponseEntity<MemberResponse> findMember(@Authorization AuthorizationInfo authorizationInfo) {

        Member member = memberSearchService.getById(authorizationInfo.id());

        return ResponseEntity.ok(new MemberResponse(
            member.getId(),
            member.getEmail(),
            member.getName(),
            member.getMemberCash().getCash(),
            member.getRegistrationPlatform()
        ));
    }

//    @PostMapping
//    public ResponseEntity<MemberResponse> pointUse(@Authorization AuthorizationInfo authorizationInfo) {
//
//        Member member = memberSearchService.getById(authorizationInfo.id());
//
//        return ResponseEntity.ok(new MemberResponse(
//            member.getId(),
//            member.getEmail(),
//            member.getName(),
//            member.getMemberCash().getCash(),
//            member.getRegistrationPlatform()
//        ));
//    }

}
