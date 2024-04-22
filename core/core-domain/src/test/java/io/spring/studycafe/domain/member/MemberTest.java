package io.spring.studycafe.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void member_create_test() {
        Member member = new Member(1L, "email", "good", RegistrationPlatform.DEFAULT);
        assertThat(member.getId()).isNotNull();
        assertThat(member.getEmail()).isEqualTo("email");
        assertThat(member.getName()).isEqualTo("good");
        assertThat(member.getRegistrationPlatform()).isEqualTo(RegistrationPlatform.DEFAULT);
    }

}