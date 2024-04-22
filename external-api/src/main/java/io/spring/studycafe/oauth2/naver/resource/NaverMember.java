package io.spring.studycafe.oauth2.naver.resource;

public record NaverMember(
    String id,
    String nickname,
    String email,
    String name
) {
}