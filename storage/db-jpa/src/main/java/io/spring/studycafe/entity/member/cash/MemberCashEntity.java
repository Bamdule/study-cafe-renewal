package io.spring.studycafe.entity.member.cash;

import io.spring.studycafe.domain.member.cash.MemberCash;
import io.spring.studycafe.entity.member.MemberEntity;
import jakarta.persistence.*;

@Table(name = "member_cash")
@Entity
public class MemberCashEntity {
    @Id
    @Column(name="member_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(name = "cash", nullable = false)
    private long cash;


    public MemberCashEntity(long cash, MemberEntity member) {
        this.member = member;
        this.cash = cash;
    }

    protected MemberCashEntity() {
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
        return new MemberCash(cash, member.getId());
    }
}
