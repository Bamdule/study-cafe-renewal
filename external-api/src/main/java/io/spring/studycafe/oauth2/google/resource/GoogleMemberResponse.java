package io.spring.studycafe.oauth2.google.resource;

import io.spring.studycafe.applcation.member.oauth2.OAuth2MemberResponse;

//  "id": "111607076395103552999",
//      "email": "bamdule5@gmail.com",
//      "verified_email": true,
//      "name": "김민우",
//      "given_name": "민우",
//      "family_name": "김",
//      "picture": "https://lh3.googleusercontent.com/a/ACg8ocKE3m3ifOmJXJ0ulXI0p9wzf3eqK4_ySz8isTpEaQuqNS35kg=s96-c",
//      "locale": "ko"
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
            "SUCCESS",
            "SUCCESS",
            id,
            name,
            email,
            name,
            status,
            message,
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
