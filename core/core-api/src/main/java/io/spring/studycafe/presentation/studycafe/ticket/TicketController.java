package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.applcation.studycafe.ticket.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/study-cafes/{studyCafeId}/tickets")
@RestController
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findByStudyCafeId(
        @PathVariable(value = "studyCafeId") Long studyCafeId
    ) {
        List<TicketResponse> responses = ticketService.findAllByStudyCafeId(studyCafeId)
            .stream()
            .map(ticketInfo -> new TicketResponse(
                ticketInfo.id(),
                ticketInfo.name(),
                ticketInfo.type(),
                ticketInfo.timeInfo(),
                ticketInfo.price(),
                ticketInfo.expirationDays()
            ))
            .toList();

        return ResponseEntity.ok(responses);
    }
}
