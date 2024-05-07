package io.spring.studycafe.domain.member;

import io.spring.studycafe.domain.member.cash.MemberCash;

public class Member {
    private Long id;

    private String email;

    private String name;

    private MemberCash memberCash;

    private RegistrationPlatform registrationPlatform;

    public Member(Long id, String email, String name, MemberCash memberCash, RegistrationPlatform registrationPlatform) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.memberCash = memberCash;
        this.registrationPlatform = registrationPlatform;
    }

    public Member(String email, String name, RegistrationPlatform registrationPlatform) {
        this.email = email;
        this.name = name;
        this.memberCash = new MemberCash();
        this.registrationPlatform = registrationPlatform;
    }

    public void useCash(Long amount) {
        memberCash.useCash(amount);
    }

    public void rechargeCash(Long amount) {
        memberCash.rechargeCash(amount);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public MemberCash getMemberCash() {
        return memberCash;
    }

    public String getName() {
        return name;


    }

    public RegistrationPlatform getRegistrationPlatform() {
        return registrationPlatform;
    }
}
