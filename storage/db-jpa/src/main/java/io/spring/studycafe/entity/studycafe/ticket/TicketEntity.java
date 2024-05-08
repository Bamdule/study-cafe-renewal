package io.spring.studycafe.entity.studycafe.ticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import io.spring.studycafe.entity.common.BaseModelEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "ticket")
@Entity
public class TicketEntity extends BaseModelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TicketType type;


    @AttributeOverrides({
        @AttributeOverride(name = "days", column = @Column(name = "provide_days")),
        @AttributeOverride(name = "hours", column = @Column(name = "provide_hours")),
        @AttributeOverride(name = "minutes", column = @Column(name = "provide_minutes"))
    })
    @Embedded
    private TimeInfo timeInfo;


    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "expiration_days", nullable = false)
    private int expirationDays;

    @Column(name = "study_cafeId", nullable = false)
    private Long studyCafeId;

    protected TicketEntity() {
    }

    public TicketEntity(String name, TicketType type, TimeInfo timeInfo, long price, int expirationDays, Long studyCafeId) {
        this.name = name;
        this.type = type;
        this.timeInfo = timeInfo;
        this.price = price;
        this.expirationDays = expirationDays;
        this.studyCafeId = studyCafeId;
    }

    public static TicketEntity of(Ticket ticket) {
        return new TicketEntity(
            ticket.getName(),
            ticket.getType(),
            ticket.getTimeInfo(),
            ticket.getPrice(),
            ticket.getExpirationDays(),
            ticket.getStudyCafeId()
        );
    }

    public Ticket to() {
        return new Ticket(
            this.id,
            this.name,
            this.type,
            this.timeInfo,
            this.price,
            this.expirationDays,
            this.studyCafeId,
            this.getCreatedAt(),
            this.getUpdatedAt()
        );
    }
}
