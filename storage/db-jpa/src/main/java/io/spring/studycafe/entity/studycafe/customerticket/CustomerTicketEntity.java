package io.spring.studycafe.entity.studycafe.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import io.spring.studycafe.entity.common.BaseModelEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Table(name = "customer_ticket")
@Entity
public class CustomerTicketEntity extends BaseModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Embedded
    private TimeInfo timeInfo;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    protected CustomerTicketEntity() {
    }

    public CustomerTicketEntity(Long customerId, TicketType ticketType, TimeInfo timeInfo, LocalDateTime expirationDate) {
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.timeInfo = timeInfo;
        this.expirationDate = expirationDate;
    }

    public void updateTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = timeInfo;
    }

    public static CustomerTicketEntity of(CustomerTicket customerTicket) {
        return new CustomerTicketEntity(
            customerTicket.getCustomerId(),
            customerTicket.getTicketType(),
            customerTicket.getTimeInfo(),
            customerTicket.getExpirationDate()
        );
    }

    public CustomerTicket to() {
        return new CustomerTicket(
            id,
            customerId,
            ticketType,
            timeInfo,
            expirationDate,
            getCreatedAt(),
            getUpdatedAt()
        );
    }
}
