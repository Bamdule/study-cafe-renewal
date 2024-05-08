package io.spring.studycafe.entity.member;

import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import io.spring.studycafe.domain.member.cash.MemberCash;
import io.spring.studycafe.entity.member.cash.MemberCashEntity;
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


    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberCashEntity memberCash;

    public MemberEntity(String email, String name, RegistrationPlatform registrationPlatform) {
        this.email = email;
        this.name = name;
        this.registrationPlatform = registrationPlatform;
        this.memberCash = new MemberCashEntity(MemberCash.DEFAULT_CASH, this);
    }

    public static MemberEntity of(Member member) {
        return new MemberEntity(
            member.getEmail(),
            member.getName(),
            member.getRegistrationPlatform()
        );
    }

    public void updateMemberCash(long cash) {
        memberCash.updateCash(cash);
    }

    public Member toModel() {
        return new Member(id, email, name, memberCash.toModel(), registrationPlatform);
    }
}
