package io.spring.studycafe.applcation.studycafe.ticket;

import io.spring.studycafe.domain.studycafe.StudyCafeRepository;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final StudyCafeRepository studyCafeRepository;

    public TicketService(TicketRepository ticketRepository, StudyCafeRepository studyCafeRepository) {
        this.ticketRepository = ticketRepository;
        this.studyCafeRepository = studyCafeRepository;
    }

    public TicketInfo register(TicketRegistrationCommand command) {
        Ticket ticket = ticketRepository.save(command.toEntity());
        return TicketInfo.create(ticket);
    }

    public List<TicketInfo> findAllByStudyCafeId(Long studyCafeId) {
        return ticketRepository.findAllByStudyCafeId(studyCafeId)
            .stream()
            .map(TicketInfo::create)
            .toList();
    }
}
