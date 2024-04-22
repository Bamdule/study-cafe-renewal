package io.spring.studycafe.domain.member;

public class Member {
    private Long id;

    private String email;

    private String name;

    private RegistrationPlatform registrationPlatform;

    public Member(Long id, String email, String name, RegistrationPlatform registrationPlatform) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.registrationPlatform = registrationPlatform;
    }

    public Member(String email, String name, RegistrationPlatform registrationPlatform) {
        this.email = email;
        this.name = name;
        this.registrationPlatform = registrationPlatform;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public RegistrationPlatform getRegistrationPlatform() {
        return registrationPlatform;
    }
}
