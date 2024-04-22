package io.spring.studycafe.domain.member;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;

public class MemberNotFoundException extends DomainException {
    public MemberNotFoundException(ExceptionCode code) {
        super(code);
    }
}
