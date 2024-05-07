package io.spring.studycafe.domain.member.cash;

import java.util.Optional;

public interface MemberCashRepository {
    MemberCash save(MemberCash memberCash);

    Optional<MemberCash> findByMemberId(Long memberId);

}
