package io.spring.studycafe.entity.studycafe.seat;

import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatState;
import io.spring.studycafe.entity.studycafe.customer.CustomerEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(
    name = "seat",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_seat_number_study_cafe_id",
            columnNames = {"number", "study_cafe_id"}
        ),
        @UniqueConstraint(
            name = "uk_seat_study_cafe_id_customer_id",
            columnNames = {"study_cafe_id", "customer_id"}
        )
    }
)
@Entity
public class SeatEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @Column(name = "study_cafe_id", nullable = false)
    private Long studyCafeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, columnDefinition = "varchar(20) default 'EMPTY'")
    private SeatState state;

    @Column(name = "seat_usage_start_datetime")
    private LocalDateTime seatUsageStartDateTime;

    protected SeatEntity() {
    }

    public SeatEntity(int number, CustomerEntity customer, Long studyCafeId, SeatState state) {
        this.number = number;
        this.customer = customer;
        this.studyCafeId = studyCafeId;
        this.state = state;
    }

    public static SeatEntity of(Seat seat, CustomerEntity customer) {
        return new SeatEntity(seat.getNumber(), customer, seat.getStudyCafeId(), seat.getState());
    }

    public Seat toModel() {
        return new Seat(
            this.id,
            this.number,
            this.customer == null ? null : this.customer.to(),
            this.studyCafeId,
            this.state,
            this.seatUsageStartDateTime
        );
    }

    public void updateEmpty() {
        this.customer = null;
        this.state = SeatState.EMPTY;
        this.seatUsageStartDateTime = null;
    }

    public void updateInUse(Seat seat, CustomerEntity customer) {
        this.customer = customer;
        this.state = SeatState.IN_USE;
        this.seatUsageStartDateTime = seat.getSeatUsageStartDateTime();
    }
}
