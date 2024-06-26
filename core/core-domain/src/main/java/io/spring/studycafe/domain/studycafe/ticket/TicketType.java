package io.spring.studycafe.domain.studycafe.ticket;

public enum TicketType {
    PERIOD("기간권"), TIME("시간권"), NONE("없음");

    private final String description;

    TicketType(String description) {
        this.description = description;
    }
}
