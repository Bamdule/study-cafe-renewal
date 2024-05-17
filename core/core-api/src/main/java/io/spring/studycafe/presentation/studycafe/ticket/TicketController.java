package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.applcation.studycafe.ticket.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "스터디카페 이용권")
@RequestMapping("/api/v1/study-cafes/{studyCafeId}/tickets")
@RestController
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "스터디 카페 이용권 조회", description = "스터디 카페 이용권을 조회한다")
    @Parameter(name = "studyCafeId", description = "스커디카페 고유번호", in = PATH, required = true)
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
