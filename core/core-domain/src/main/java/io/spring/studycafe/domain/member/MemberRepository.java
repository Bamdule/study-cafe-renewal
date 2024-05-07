package io.spring.studycafe.domain.member;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByIdWithPessimisticLocking(Long id);

    Member save(Member member);

    Member updateCash(Member member);
}
