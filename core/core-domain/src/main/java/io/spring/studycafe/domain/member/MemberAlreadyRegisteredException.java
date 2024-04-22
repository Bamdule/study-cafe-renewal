package io.spring.studycafe.domain.member;

import io.spring.studycafe.domain.common.ExceptionCode;
import io.spring.studycafe.domain.common.exception.DomainException;
import org.springframework.http.HttpStatus;

public class MemberAlreadyRegisteredException extends DomainException {
    public MemberAlreadyRegisteredException(ExceptionCode code) {
        super(code);
    }
}
