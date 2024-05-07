package io.spring.studycafe.config;

import io.spring.studycafe.applcation.member.MemberRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InterceptorTest {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private MemberRegistrationService memberRegistrationService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo(print())
            .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            .build();
    }


    //ACCESS_TOKEN_REQUIRED 테스트
    //JWT_SIGNATURE_INVALID 테스트
    //JWT_EXPIRED 테스트
    //
}
