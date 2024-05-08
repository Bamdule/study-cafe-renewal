package io.spring.studycafe.entity.studycafe.customer;

import io.spring.studycafe.domain.common.BaseModel;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "customer")
@Entity
public class CustomerEntity extends BaseModel {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "study_cafe_id", nullable = false)
    private Long studyCafeId;

    public CustomerEntity(Long memberId, Long studyCafeId) {
        this.memberId = memberId;
        this.studyCafeId = studyCafeId;
    }

    public Customer to() {
        return new Customer(this.id, this.memberId, this.studyCafeId, this.getCreatedAt(), this.getUpdatedAt());
    }

    public static CustomerEntity of(Customer customer) {
        return new CustomerEntity(
            customer.getMemberId(),
            customer.getStudyCafeId()
        );
    }
}
