package io.spring.studycafe.entity.studycafe.customer.customerticket;

import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerDefaultTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerPeriodTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTimeTicket;

import java.util.Optional;

public class CustomerTicketFactory {

    public static Optional<CustomerTicket> create(CustomerTicketEntity entity) {
        if (entity == null) {
            return Optional.empty();
        }
        switch (entity.getTicketType()) {
            case PERIOD:
                return Optional.of(
                    new CustomerPeriodTicket(
                        entity.getCustomer().getId(),
                        entity.getExpirationDate(),
                        entity.getTimeInfo(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()

                    )
                );
            case TIME:
                return Optional.of(
                    new CustomerTimeTicket(
                        entity.getCustomer().getId(),
                        entity.getExpirationDate(),
                        entity.getTimeInfo(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()
                    )
                );
            case NONE:
                return Optional.of(
                    new CustomerDefaultTicket(
                        entity.getCustomer().getId(),
                        entity.getExpirationDate(),
                        entity.getTimeInfo(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()
                    )
                );
        }

        throw new IllegalStateException("잘못된 이용권 타입입니다.");
    }
}
