package io.spring.studycafe.domain.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND),
    MEMBER_ALREADY_REGISTERED("이미 가입된 회원입니다", HttpStatus.BAD_REQUEST),

    CARD_PAYMENT_METHOD_NOT_FOUND("존재하지 않는 카드 결제수단 입니다.", HttpStatus.NOT_FOUND),

    CARD_REGISTRATION_COUNT_EXCEEDED("등록 가능한 개수를 초과했습니다.", HttpStatus.BAD_REQUEST),
    CARD_REGISTER_ADAPTER_NOT_FOUND("카드 등록 어댑터를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    CARD_DELETE_FAILURE("카드 삭제를 실패했습니다.", HttpStatus.BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
