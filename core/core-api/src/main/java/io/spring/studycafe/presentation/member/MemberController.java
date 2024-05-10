package io.spring.studycafe.presentation.member;

import io.spring.studycafe.applcation.member.MemberInfo;
import io.spring.studycafe.applcation.member.MemberSearchService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

        MemberInfo memberInfo = memberSearchService.getById(authorizationInfo.id());

        return ResponseEntity.ok(MemberResponse.of(memberInfo));
    }
}
