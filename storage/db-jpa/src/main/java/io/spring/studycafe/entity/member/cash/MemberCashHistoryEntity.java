package io.spring.studycafe.entity.member.cash;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

//@Table(name = "member_cash_history")
//@Entity
public class MemberCashHistoryEntity {

    @Id
    private Long memberId;

    @Column(name = "cash", nullable = false)
    private long cash;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberCashHistoryType type;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    protected MemberCashHistoryEntity() {
    }

    public MemberCashHistoryEntity(Long memberId, long cash, MemberCashHistoryType type) {
        this.memberId = memberId;
        this.cash = cash;
        this.type = type;
        this.createAt = LocalDateTime.now();
    }


    public Long getMemberId() {
        return memberId;
    }

    public long getCash() {
        return cash;
    }
}
