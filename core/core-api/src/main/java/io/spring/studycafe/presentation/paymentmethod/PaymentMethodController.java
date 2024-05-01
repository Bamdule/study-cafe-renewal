package io.spring.studycafe.presentation.paymentmethod;

import io.spring.studycafe.applcation.paymentmethod.card.CardRegistrationCommand;
import io.spring.studycafe.applcation.paymentmethod.card.CardRegistrationResult;
import io.spring.studycafe.applcation.paymentmethod.card.CardRegistrationService;
import io.spring.studycafe.applcation.paymentmethod.card.CardSearchService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/payment-methods")
@RestController
public class PaymentMethodController {

    private final CardRegistrationService cardRegistrationService;
    private final CardSearchService cardSearchService;

    public PaymentMethodController(CardRegistrationService cardRegistrationService, CardSearchService cardSearchService) {
        this.cardRegistrationService = cardRegistrationService;
        this.cardSearchService = cardSearchService;
    }

    @PostMapping
    ResponseEntity<Object> registerPaymentMethod(@Authorization AuthorizationInfo authorizationInfo) {
        CardRegistrationResult result = cardRegistrationService.register(new CardRegistrationCommand(
            authorizationInfo.id(),
            CardPaymentAgency.NICEPAY,
            "",
            "",
            "",
            "",
            ""
        ));

        return ResponseEntity.ok(result);
    }

    @GetMapping
    ResponseEntity<Object> searchPaymentMethod(@Authorization AuthorizationInfo authorizationInfo) {
        return ResponseEntity.ok(cardSearchService.search(authorizationInfo.id()));
    }
}
