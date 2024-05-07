package io.spring.studycafe.oauth2.google.resource;

import io.spring.studycafe.applcation.member.oauth2.adapter.OAuth2MemberResponse;

public record GoogleMemberResponse(
    String id,
    String email,
    String name,
    String message,
    String code,
    String status
) {
    public boolean isSuccess() {
        return id != null;
    }

    public OAuth2MemberResponse to() {

        return new OAuth2MemberResponse(
            id,
            email,
            name,
            name,
            message,
            status,
            "SUCCESS",
            "SUCCESS",
            isSuccess()
        );
    }

    public static OAuth2MemberResponse failure(String message) {
        return new OAuth2MemberResponse(
            "",
            "",
            "",
            "",
            "",
            "",
            "error",
            message,
            false
        );
    }
}
