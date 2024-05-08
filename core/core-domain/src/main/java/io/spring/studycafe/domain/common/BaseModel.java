package io.spring.studycafe.domain.common;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseModel {

    public BaseModel() {
    }

    public BaseModel(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
