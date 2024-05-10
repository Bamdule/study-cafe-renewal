package io.spring.studycafe.entity.studycafe.customer;

import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.entity.common.BaseModelEntity;
import io.spring.studycafe.entity.studycafe.customerticket.CustomerTicketEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "customer",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_customer_member_id_study_cafe_id",
            columnNames = {"member_id", "study_cafe_id"}
        )}
)
@Entity
public class CustomerEntity extends BaseModelEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "study_cafe_id", nullable = false)
    private Long studyCafeId;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CustomerTicketEntity customerTicket;

    protected CustomerEntity() {
    }

    public CustomerEntity(Customer customer) {
        this.memberId = customer.getMemberId();
        this.studyCafeId = customer.getStudyCafeId();
        this.customerTicket = new CustomerTicketEntity(this, customer.getCustomerTicket());
    }

    public Customer to() {
        return new Customer(this.id, this.memberId, this.studyCafeId, this.customerTicket.to(), this.getCreatedAt(), this.getUpdatedAt());
    }

    public static CustomerEntity of(Customer customer) {
        return new CustomerEntity(customer);
    }

    public void update(Customer customer) {
        this.customerTicket.updateTicket(customer.getCustomerTicket());
    }
}
