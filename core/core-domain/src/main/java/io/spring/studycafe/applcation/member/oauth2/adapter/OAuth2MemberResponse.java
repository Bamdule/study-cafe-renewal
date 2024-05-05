package io.spring.studycafe.applcation.member.oauth2.adapter;

public record OAuth2MemberResponse(
    String id,
    String email,
    String name,
    String nickname,
    String message,
    String code,
    String error,
    String errorDescription,
    boolean success
) {
}
