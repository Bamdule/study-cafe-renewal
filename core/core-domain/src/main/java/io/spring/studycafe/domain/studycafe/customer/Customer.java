package io.spring.studycafe.domain.studycafe.customer;

import io.spring.studycafe.domain.common.BaseModel;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Customer extends BaseModel {
    private Long id;
    private Long memberId;
    private Long studyCafeId;

    public Customer(Long memberId, Long studyCafeId) {
        this.memberId = memberId;
        this.studyCafeId = studyCafeId;
    }

    public Customer(Long id, Long memberId, Long studyCafeId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.memberId = memberId;
        this.studyCafeId = studyCafeId;
    }
}
