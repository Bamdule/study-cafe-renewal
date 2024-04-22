package io.spring.studycafe.presentation.member.oauth2;

import io.spring.studycafe.applcation.member.MemberRegistrationCommand;
import io.spring.studycafe.applcation.member.MemberService;
import io.spring.studycafe.applcation.member.oauth2.*;
import io.spring.studycafe.applcation.member.oauth2.authorization.OAuth2AuthorizationUrlProvider;
import io.spring.studycafe.authorization.AuthorizationManager;
import io.spring.studycafe.authorization.AuthorizationPayload;
import io.spring.studycafe.authorization.AuthorizationToken;
import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberAlreadyRegisteredException;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import io.spring.studycafe.presentation.member.MemberRegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/oauth2-member-registration")
@RestController
public class MemberOAuth2Controller {

    private final OAuth2AuthorizationUrlProvider oAuth2AuthorizationUrlProvider;
    private final MemberService memberService;
    private final OAuth2ClientAdapter oAuth2ClientAdapter;
    private final AuthorizationManager authorizationManager;

    public MemberOAuth2Controller(OAuth2AuthorizationUrlProvider oAuth2AuthorizationUrlProvider, MemberService memberService, OAuth2ClientAdapter oAuth2ClientAdapter, AuthorizationManager authorizationManager) {
        this.oAuth2AuthorizationUrlProvider = oAuth2AuthorizationUrlProvider;
        this.memberService = memberService;
        this.oAuth2ClientAdapter = oAuth2ClientAdapter;
        this.authorizationManager = authorizationManager;
    }

    @GetMapping(value = "/authorization-url")
    ResponseEntity<String> oauth2Callback(OAuth2Platform platform) {
        String authorizationUrl = oAuth2AuthorizationUrlProvider.provide(platform)
            .create();
        return ResponseEntity.ok(authorizationUrl);
    }

    /**
     * OAuth2 Authorization Server에서 호출하는 API
     * 인증 성공 시 Resource AccessToken으로 Platform member resource를 요청한다.
     * member 정보를 조회해서 존재하면 자체 accessToken을 생성해서 반환한다
     * 없으면 회원가입을 진행하고 AccessToken을 반환한다
     *
     * @param code     인증 코드
     * @param state    CSRF 공격 대비 state
     * @param platform Authorization Server 플랫폼 타입
     * @return
     */
    @GetMapping
    ResponseEntity<MemberRegistrationResponse> register(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state, @RequestParam(value = "platform") OAuth2Platform platform) {
        OAuth2AccessTokenResponse accessTokenResponse = oAuth2ClientAdapter.requestAccessToken(new OAuth2AccessTokenRequest(code, state, platform));
        OAuth2MemberResponse memberResponse = oAuth2ClientAdapter.requestMember(new OAuth2MemberRequest(accessTokenResponse.accessToken(), platform));

        MemberRegistrationCommand command = new MemberRegistrationCommand(memberResponse.email(), memberResponse.name(), platform.getRegistrationPlatform());
        Member member = memberService.findByEmail(memberResponse.email())
            .orElseGet(() -> memberService.register(command));

        if (member.getRegistrationPlatform() == RegistrationPlatform.DEFAULT) {
            throw new MemberAlreadyRegisteredException(ExceptionCode.MEMBER_ALREADY_REGISTERED);
        }

        AuthorizationToken authorizationResponse = authorizationManager.generateAuthorization(new AuthorizationPayload(member.getId(), member.getEmail(), member.getName()));

        return ResponseEntity.ok(new MemberRegistrationResponse(authorizationResponse.accessToken(), authorizationResponse.refreshToken()));
    }
}
