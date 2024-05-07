package io.spring.studycafe.entity.member.cash;

import io.spring.studycafe.domain.member.cash.MemberCash;
import io.spring.studycafe.entity.member.MemberEntity;
import jakarta.persistence.*;

@Table(name = "member_cash")
@Entity
public class MemberCashEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cash", nullable = false)
    private long cash;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    public MemberCashEntity(Long id, long cash, MemberEntity member) {
        this.id = id;
        this.member = member;
        this.cash = cash;
    }

    protected MemberCashEntity() {
    }

    public Long getId() {
        return id;
    }

    public long getCash() {
        return cash;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void updateCash(long cash) {
        this.cash = cash;
    }

    public MemberCash toModel() {
        return new MemberCash(id, cash, member.getId());
    }
}
