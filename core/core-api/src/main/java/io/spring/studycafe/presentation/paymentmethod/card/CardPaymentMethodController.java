package io.spring.studycafe.presentation.paymentmethod.card;

import io.spring.studycafe.applcation.paymentmethod.card.*;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "카드 결제 수단")
@RequestMapping(value = "/api/v1/card-payment-methods")
@RestController
public class CardPaymentMethodController {

    private final CardRegisterService cardRegisterService;
    private final CardSearchService cardSearchService;
    private final CardDeleteService cardDeleteService;

    public CardPaymentMethodController(CardRegisterService cardRegisterService, CardSearchService cardSearchService, CardDeleteService cardDeleteService) {
        this.cardRegisterService = cardRegisterService;
        this.cardSearchService = cardSearchService;
        this.cardDeleteService = cardDeleteService;
    }

    @PostMapping
    ResponseEntity<CardPaymentMethodRegistrationResponse> registerPaymentMethod(
        @RequestBody @Valid CardPaymentMethodRegistrationRequest request,
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo
    ) {
        CardInfo result = cardRegisterService.register(
            new CardRegisterCommand(
                authorizationInfo.id(),
                request.cardNumber(),
                request.expirationYear(),
                request.expirationMonth(),
                request.cardPassword(),
                request.personalId(),
                request.cardPaymentAgency()
            ));

        return ResponseEntity.ok(new CardPaymentMethodRegistrationResponse(result.message(), result.success()));
    }


    @GetMapping
    ResponseEntity<List<CardPaymentMethod>> findAllCards(@Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo) {
        return ResponseEntity.ok(cardSearchService.findAllByMemberId(authorizationInfo.id()));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CardPaymentMethodRegistrationResponse> deletePaymentMethod(@PathVariable(value = "id") Long id) {
        CardDeleteResult result = cardDeleteService.delete(new CardDeleteCommand(id));

        return ResponseEntity.ok(new CardPaymentMethodRegistrationResponse(result.message(), result.success()));
    }

}
