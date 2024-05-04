package io.spring.studycafe.presentation.paymentmethod.card;

import io.spring.studycafe.applcation.paymentmethod.card.CardRegistrationCommand;
import io.spring.studycafe.applcation.paymentmethod.card.CardRegistrationResult;
import io.spring.studycafe.applcation.paymentmethod.card.CardRegistrationService;
import io.spring.studycafe.applcation.paymentmethod.card.CardSearchService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentMethod;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/card-payment-methods")
@RestController
public class CardPaymentMethodController {

    private final CardRegistrationService cardRegistrationService;
    private final CardSearchService cardSearchService;

    public CardPaymentMethodController(CardRegistrationService cardRegistrationService, CardSearchService cardSearchService) {
        this.cardRegistrationService = cardRegistrationService;
        this.cardSearchService = cardSearchService;
    }

    @PostMapping
    ResponseEntity<CardPaymentMethodRegistrationResponse> registerPaymentMethod(
        @RequestBody @Valid CardPaymentMethodRegistrationRequest request,
        @Authorization AuthorizationInfo authorizationInfo
    ) {
        CardRegistrationResult result = cardRegistrationService.register(
            new CardRegistrationCommand(
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
    ResponseEntity<List<CardPaymentMethod>> findAllCards(@Authorization AuthorizationInfo authorizationInfo) {
        return ResponseEntity.ok(cardSearchService.findAllByMemberId(authorizationInfo.id()));
    }
}
