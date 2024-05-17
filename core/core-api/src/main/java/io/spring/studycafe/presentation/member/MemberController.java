package io.spring.studycafe.presentation.member;

import io.spring.studycafe.applcation.member.MemberInfo;
import io.spring.studycafe.applcation.member.MemberSearchService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@RequestMapping(value = "/api/v1/members")
@RestController
public class MemberController {

    private final MemberSearchService memberSearchService;

    public MemberController(MemberSearchService memberSearchService) {
        this.memberSearchService = memberSearchService;
    }

    @Operation(summary = "회원 조회", description = "회원 정보를 조회한다")
    @GetMapping
    public ResponseEntity<MemberResponse> findMember(@Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo) {

        MemberInfo memberInfo = memberSearchService.getById(authorizationInfo.id());

        return ResponseEntity.ok(MemberResponse.of(memberInfo));
    }
}
