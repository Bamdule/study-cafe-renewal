package io.spring.studycafe.domain.studycafe;

import io.spring.studycafe.domain.common.BaseModel;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyCafe extends BaseModel {
    public final String PHONE_NUMBER_REGEX = "^\\d{3}-\\d{3,4}-\\d{4}$";

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Long memberId;

    public StudyCafe(String name, String address, String phoneNumber, Long memberId) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memberId = memberId;
    }

    public StudyCafe(Long id, String name, String address, String phoneNumber, Long memberId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memberId = memberId;
    }

    public static StudyCafe initialize(String name, String address, String phoneNumber, Long memberId) {
        return new StudyCafe(name, address, phoneNumber, memberId);
    }

    public boolean isOwner(Long memberId) {
        return this.getMemberId().equals(memberId);
    }
}
