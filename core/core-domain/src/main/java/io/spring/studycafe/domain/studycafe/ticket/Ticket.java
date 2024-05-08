package io.spring.studycafe.domain.studycafe.ticket;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.common.TimeInfo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Ticket extends BaseModel {
    private Long id;
    private String name;
    private TicketType type;
    private TimeInfo timeInfo;
    private long price;
    private int expirationDays;
    private Long studyCafeId;

    public Ticket(Long id, String name, TicketType type, TimeInfo timeInfo, long price, int expirationDays, Long studyCafeId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeInfo = timeInfo;
        this.price = price;
        this.expirationDays = expirationDays;
        this.studyCafeId = studyCafeId;
    }

    public Ticket(String name, TicketType type, TimeInfo timeInfo, long price, int expirationDays, Long studyCafeId) {
        this.name = name;
        this.type = type;
        this.timeInfo = timeInfo;
        this.price = price;
        this.expirationDays = expirationDays;
        this.studyCafeId = studyCafeId;
    }
}
