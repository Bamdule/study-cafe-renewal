package io.spring.studycafe.domain.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND),
    MEMBER_ALREADY_REGISTERED("이미 가입된 회원입니다", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND("존재하지 않는 고객입니다.", HttpStatus.NOT_FOUND),
    CUSTOMER_ALREADY_REGISTERED("이미 가입한 고객입니다", HttpStatus.BAD_REQUEST),
    CUSTOMER_NO_TICKET("티켓을 소지하고 있지 않습니다.", HttpStatus.BAD_REQUEST),


    STUDY_CAFE_NOT_FOUND("존재하지 않는 스터디 카페입니다.", HttpStatus.NOT_FOUND),
    TICKET_NOT_FOUND("존재하지 않는 티켓입니다.", HttpStatus.NOT_FOUND),
    SEAT_ALREADY_IN_USE("이미 사용중인 좌석입니다.", HttpStatus.BAD_REQUEST),
    SEAT_EMPTY("빈 좌석 입니다.", HttpStatus.BAD_REQUEST),
    SEAT_NOT_FOUND("존재하지 않는 좌석입니다.", HttpStatus.NOT_FOUND),
    SEAT_INVALID("잘못된 좌석입니다.", HttpStatus.BAD_REQUEST),
    SEAT_ONLY_ONE_USABLE("한 좌석만 사용할 수 있습니다.", HttpStatus.BAD_REQUEST),

    CARD_PAYMENT_METHOD_NOT_FOUND("존재하지 않는 카드 결제수단 입니다.", HttpStatus.NOT_FOUND),

    CARD_REGISTRATION_COUNT_EXCEEDED("등록 가능한 개수를 초과했습니다.", HttpStatus.BAD_REQUEST),
    CARD_REGISTER_ADAPTER_NOT_FOUND("카드 등록 어댑터를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    CARD_DELETE_FAILURE("카드 삭제를 실패했습니다.", HttpStatus.BAD_REQUEST),

    CARD_PAYMENT_API_ADAPTER_NOT_FOUND("카드 결제 API 모듈이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    PAYMENT_SERVICE_NOT_FOUND("결제 서비스가 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    ;

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
