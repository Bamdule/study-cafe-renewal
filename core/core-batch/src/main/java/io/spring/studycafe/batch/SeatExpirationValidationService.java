package io.spring.studycafe.batch;

import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatManager;
import io.spring.studycafe.domain.studycafe.seat.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SeatExpirationValidationService {

    private final SeatRepository seatRepository;
    private final CustomerRepository customerRepository;
    private final SeatManager seatManager;

    public SeatExpirationValidationService(SeatRepository seatRepository, CustomerRepository customerRepository, SeatManager seatManager) {
        this.seatRepository = seatRepository;
        this.customerRepository = customerRepository;
        this.seatManager = seatManager;
    }

    @Transactional
    public void verifyExpiration(Seat seat) {
        try {
            Customer customer = seat.getCustomer();
            customer.useTicket(seat.getUsedTimeInfo());

            if (customer.hasNotTicket()) {
                updateTicket(customer);
                seatManager.checkout(seat.getId(), customer.getMemberId());
            }

        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    private void updateTicket(Customer customer) {
        customerRepository.update(customer);
    }
}
