package io.spring.studycafe.entity.studycafe.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import io.spring.studycafe.entity.common.BaseModelEntity;
import io.spring.studycafe.entity.studycafe.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Table(name = "customer_ticket")
@Entity
public class CustomerTicketEntity extends BaseModelEntity {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Embedded
    private TimeInfo timeInfo;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    protected CustomerTicketEntity() {
    }

    public CustomerTicketEntity(CustomerEntity customer, CustomerTicket customerTicket) {
        this.customer = customer;
        this.ticketType = customerTicket.getTicketType();
        this.timeInfo = customerTicket.getTimeInfo();
        this.expirationDate = customerTicket.getExpirationDate();
    }

    public void updateTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = timeInfo;
    }

    public void updateTicket(CustomerTicket customerTicket) {
        this.ticketType = customerTicket.getTicketType();
        this.timeInfo = customerTicket.getTimeInfo();
        this.expirationDate = customerTicket.getExpirationDate();
    }

    public CustomerTicket to() {
        return new CustomerTicket(
            customer.getId(),
            ticketType,
            timeInfo,
            expirationDate,
            getCreatedAt(),
            getUpdatedAt()
        );
    }
}
