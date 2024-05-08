package io.spring.studycafe.domain.studycafe.ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    List<Ticket> findAllByStudyCafeId(Long studyCafeId);

    Optional<Ticket> findById(Long id);

    Ticket save(Ticket ticket);
}
