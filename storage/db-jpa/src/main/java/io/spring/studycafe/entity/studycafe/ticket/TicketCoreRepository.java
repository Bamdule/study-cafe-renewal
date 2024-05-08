package io.spring.studycafe.entity.studycafe.ticket;

import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import io.spring.studycafe.domain.studycafe.ticket.TicketRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketCoreRepository implements TicketRepository {

    private final TicketJpaRepository ticketJpaRepository;

    public TicketCoreRepository(TicketJpaRepository ticketJpaRepository) {
        this.ticketJpaRepository = ticketJpaRepository;
    }

    @Override
    public List<Ticket> findAllByStudyCafeId(Long studyCafeId) {
        return ticketJpaRepository.findAllByStudyCafeId(studyCafeId)
            .stream().map(TicketEntity::to)
            .toList();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaRepository.findById(id).map(TicketEntity::to);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketJpaRepository.save(TicketEntity.of(ticket)).to();
    }
}
