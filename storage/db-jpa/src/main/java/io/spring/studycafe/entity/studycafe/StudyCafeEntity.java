package io.spring.studycafe.entity.studycafe;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.studycafe.StudyCafe;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "study_cafe")
@Entity
public class StudyCafeEntity extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    protected StudyCafeEntity() {
    }

    public StudyCafeEntity(String name, String address, String phoneNumber, Long memberId) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memberId = memberId;
    }

    public static StudyCafeEntity of(StudyCafe studyCafe) {
        return new StudyCafeEntity(
            studyCafe.getName(),
            studyCafe.getAddress(),
            studyCafe.getPhoneNumber(),
            studyCafe.getMemberId()
        );
    }

    public StudyCafe to() {
        return new StudyCafe(
            this.id,
            this.name,
            this.address,
            this.phoneNumber,
            this.memberId,
            this.getCreatedAt(),
            this.getUpdatedAt()
        );
    }
}
