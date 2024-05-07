package io.spring.studycafe.applcation.member;

public record MemberInfo(
    Long id,
    String name,
    String email,
    Long cash
) {
}
