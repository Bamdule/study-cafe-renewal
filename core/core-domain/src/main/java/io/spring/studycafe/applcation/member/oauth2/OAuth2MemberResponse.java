package io.spring.studycafe.applcation.member.oauth2;

public record OAuth2MemberResponse(
    String resultCode,
    String message,
    String id,
    String nickname,
    String email,
    String name,
    String error,
    String errorDescription,
    boolean success
) {
}
