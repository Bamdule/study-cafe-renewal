package io.spring.studycafe.entity.member;

import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
@Entity
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_platform", nullable = false)
    private RegistrationPlatform registrationPlatform;

    public MemberEntity(Long id, String email, String name, RegistrationPlatform registrationPlatform) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.registrationPlatform = registrationPlatform;
    }

    public MemberEntity(String email, String name, RegistrationPlatform registrationPlatform) {
        this(null, email, name, registrationPlatform);
    }

    public Member toModel() {
        return new Member(id, email, name, registrationPlatform);
    }

    public static MemberEntity create(Member member) {
        return new MemberEntity(member.getId(), member.getEmail(), member.getName(), member.getRegistrationPlatform());
    }
}
