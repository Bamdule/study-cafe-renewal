package io.spring.studycafe.presentation.member;

import io.spring.studycafe.applcation.member.MemberService;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MemberControllerTest {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo(print())
            .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            .build();
    }

    @DisplayName("회원 조회 테스트")
    @Test
    public void member_get_api_test() throws Exception {
        //given
        final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJkYXRhIjp7ImlkIjoxLCJlbWFpbCI6ImFsc2RuNTQzQG5hdmVyLmNvbSIsIm5hbWUiOiLquYDrr7zsmrAifSwic3ViIjoidG9rZW4iLCJleHAiOjE3MTM1NTkzNDh9.QU38L7uGHF_7oMkoygalFIsQn2nE1rDc4Ep6MGhoICRIImc4ivL2v-seRprBsEI2brOfDD67qD4am241QOfnjQ";
        Member member = new Member(1L, "email", "", RegistrationPlatform.KAKAO);

        //when
        when(memberService.getById(any())).thenReturn(member);

        mockMvc.perform(get("/api/v1/members")
                .header("ACCESS_TOKEN", accessToken))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.id").value(member.getId()))
            .andExpect(jsonPath("$.email").value(member.getEmail()))
            .andExpect(jsonPath("$.name").value(member.getName()))
            .andExpect(jsonPath("$.registrationPlatform").value(member.getRegistrationPlatform().name()))
        ;
    }
}