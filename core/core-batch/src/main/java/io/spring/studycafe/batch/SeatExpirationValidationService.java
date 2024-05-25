package io.spring.studycafe.batch;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customer.Customer;
import io.spring.studycafe.domain.studycafe.customer.CustomerRepository;
import io.spring.studycafe.domain.studycafe.customer.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.seat.Seat;
import io.spring.studycafe.domain.studycafe.seat.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
public class SeatExpirationValidationService {

    private final SeatRepository seatRepository;
    private final CustomerRepository customerRepository;

    public SeatExpirationValidationService(SeatRepository seatRepository, CustomerRepository customerRepository) {
        this.seatRepository = seatRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void verifyExpiration(Seat seat) {
        try {
            TimeInfo usedTimeInfo = seat.getUsedTimeInfo();
            Customer customer = seat.getCustomer();

            CustomerTicket customerTicket = customer.getCustomerTicket();

            switch (customerTicket.getTicketType()) {
                case TIME:
                    if (isExpiredTimeTicket(customerTicket, usedTimeInfo)) {
                        deductTimeTicket(customer);
                        leaveSeat(seat);
                    }
                    break;
                case PERIOD:
                    if (isExpiredTicket(customerTicket)) {
                        leaveSeat(seat);
                    }
                    break;
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    private boolean isExpiredTicket(CustomerTicket customerTicket) {
        return customerTicket.getExpirationDate().isBefore(LocalDate.now());
    }

    private void deductTimeTicket(Customer customer) {
        customer.getCustomerTicket().deductAllTime();
        customerRepository.update(customer);
    }

    private void leaveSeat(Seat seat) {
        seat.leave();
        seatRepository.update(seat);
    }

    private boolean isExpiredTimeTicket(CustomerTicket customerTicket, TimeInfo usedTimeInfo) {
        return customerTicket.getTimeInfo().isSmallerThan(usedTimeInfo);
    }

}
