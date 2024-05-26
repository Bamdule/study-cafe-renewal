package io.spring.studycafe.presentation.studycafe.ticket;

import io.spring.studycafe.applcation.payment.PaymentInfo;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketPaymentCommand;
import io.spring.studycafe.applcation.payment.ticket.TicketPaymentService;
import io.spring.studycafe.applcation.studycafe.ticket.TicketService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "스터디카페 이용권")
@RequestMapping("/api/v1/study-cafe-tickets")
@RestController
public class TicketController {
    private final TicketService ticketService;
    private final TicketPaymentService ticketPaymentService;

    public TicketController(TicketService ticketService, TicketPaymentService ticketPaymentService) {
        this.ticketService = ticketService;
        this.ticketPaymentService = ticketPaymentService;
    }

    @Operation(summary = "스터디 카페 이용권 조회", description = "스터디 카페 이용권을 조회한다")
    @GetMapping
    public ResponseEntity<List<TicketResponse>> findByStudyCafeId(
        @RequestParam(value = "studyCafeId") Long studyCafeId
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

    @PostMapping("/{ticketId}/payment")
    public ResponseEntity<TicketPaymentResponse> purchaseTicket(
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo,
        @PathVariable(value = "ticketId") Long ticketId,
        @RequestBody @Valid TicketPaymentRequest request) {
        PaymentInfo paymentInfo = ticketPaymentService.purchase(
            new CustomerTicketPaymentCommand(
                request.studyCafeId(),
                authorizationInfo.id(),
                ticketId,
                request.paymentMethodType(),
                request.paymentMethodId()
            )
        );

        return ResponseEntity.ok(TicketPaymentResponse.of(paymentInfo));
    }
}
