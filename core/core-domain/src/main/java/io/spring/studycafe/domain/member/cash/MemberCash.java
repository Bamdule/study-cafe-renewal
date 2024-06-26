package io.spring.studycafe.domain.member.cash;

public class MemberCash {

    public static final long DEFAULT_CASH = 0;
    private long cash;
    private Long memberId;

    public MemberCash(long cash, Long memberId) {
        this.cash = cash;
        this.memberId = memberId;
    }

    public MemberCash() {
        this(DEFAULT_CASH, null);
    }

    public void useCash(long amount) {
        if (amount < 0) {
            throw new IllegalStateException("음수 금액은 사용할 수 없습니다.");
        }

        if (this.cash < amount) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }

        this.cash -= amount;
    }

    public void rechargeCash(long amount) {
        if (amount < 0) {
            throw new IllegalStateException("음수 금액은 충전할 수 없습니다");
        }

        this.cash += amount;
    }

    public long getCash() {
        return cash;
    }

    public Long getMemberId() {
        return memberId;
    }
}
