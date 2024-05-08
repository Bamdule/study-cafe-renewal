package io.spring.studycafe.entity.studycafe.customerticket;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.common.exception.DomainException;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicketRepository;
import io.spring.studycafe.domain.studycafe.ticket.TicketType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class CustomerTicketCoreRepository implements CustomerTicketRepository {
    private final CustomerTicketJpaRepository customerTicketJpaRepository;

    public CustomerTicketCoreRepository(CustomerTicketJpaRepository customerTicketJpaRepository) {
        this.customerTicketJpaRepository = customerTicketJpaRepository;
    }


    @Override
    public Optional<CustomerTicket> findByCustomerId(Long customerId) {
        return customerTicketJpaRepository.findByCustomerId(customerId)
            .map(CustomerTicketEntity::to);
    }

    @Override
    public CustomerTicket save(CustomerTicket customerTicket) {
        return customerTicketJpaRepository.save(CustomerTicketEntity.of(customerTicket)).to();
    }

    @Transactional
    @Override
    public CustomerTicket deduction(CustomerTicket customerTicket, TimeInfo usageTime) {
        CustomerTicketEntity customerTicketEntity = customerTicketJpaRepository.findByCustomerId(customerTicket.getCustomerId())
            .orElseThrow(() -> new DomainException("고객의 티켓정보가 존재하지 않습니다", "NOT_FOUND_CUSTOMER_TICKET", 400));


        if (TicketType.TIME != customerTicket.getTicketType()) {
            new DomainException("시간 차감형 티켓이 아닙니다", "NOT_TIME_DEDUCTION_TICKET", 400);
        }

        TimeInfo remainingTimeInfo = TimeInfo.subtract(customerTicket.getTimeInfo(), usageTime);
        customerTicketEntity.updateTimeInfo(remainingTimeInfo);
        return customerTicketEntity.to();
    }

    @Override
    public void delete(Long id) {
        customerTicketJpaRepository.deleteById(id);
    }
}
