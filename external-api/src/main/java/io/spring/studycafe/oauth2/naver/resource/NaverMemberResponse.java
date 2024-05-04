package io.spring.studycafe.oauth2.naver.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.spring.studycafe.applcation.member.oauth2.OAuth2MemberResponse;

public record NaverMemberResponse(
    @JsonProperty("resultcode") String resultCode,
    @JsonProperty("message") String message,
    @JsonProperty("response") NaverMember member,
    @JsonProperty("error") String error,
    @JsonProperty("error_description") String errorDescription
) {
    public boolean isSuccess() {
        return error == null;
    }

    public OAuth2MemberResponse to() {

        return new OAuth2MemberResponse(
            member.id(),
            member.email(),
            member.name(),
            member.nickname(),
            message,
            resultCode,
            "",
            "",
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
