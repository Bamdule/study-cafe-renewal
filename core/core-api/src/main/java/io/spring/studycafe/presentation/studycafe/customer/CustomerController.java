package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.member.MemberInfo;
import io.spring.studycafe.applcation.member.MemberSearchService;
import io.spring.studycafe.applcation.payment.PaymentResult;
import io.spring.studycafe.applcation.studycafe.customer.CustomerInfo;
import io.spring.studycafe.applcation.studycafe.customer.CustomerRegistrationCommand;
import io.spring.studycafe.applcation.studycafe.customer.CustomerService;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketPaymentCommand;
import io.spring.studycafe.applcation.studycafe.customer.customerticket.CustomerTicketPaymentService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/study-cafe-customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final MemberSearchService memberSearchService;
    private final CustomerTicketPaymentService customerTicketPaymentService;

    public CustomerController(CustomerService customerService, MemberSearchService memberSearchService, CustomerTicketPaymentService customerTicketPaymentService) {
        this.customerService = customerService;
        this.memberSearchService = memberSearchService;
        this.customerTicketPaymentService = customerTicketPaymentService;
    }

    @GetMapping
    public ResponseEntity<CustomerAggregateResponse> findCustomer(
        @Authorization AuthorizationInfo authorizationInfo,
        @RequestParam(value = "studyCafeId") Long studyCafeId
    ) {
        MemberInfo memberInfo = memberSearchService.getById(authorizationInfo.id());
        CustomerInfo customerInfo = customerService.find(authorizationInfo.id(), studyCafeId);

        return ResponseEntity.ok(CustomerAggregateResponse.of(memberInfo, customerInfo));
    }

    @PostMapping
    public ResponseEntity<CustomerAggregateResponse> registerCustomer(
        @Authorization AuthorizationInfo authorizationInfo,
        @RequestParam(value = "studyCafeId") Long studyCafeId
    ) {
        MemberInfo memberInfo = memberSearchService.getById(authorizationInfo.id());
        CustomerInfo customerInfo = customerService.register(new CustomerRegistrationCommand(authorizationInfo.id(), studyCafeId));

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerAggregateResponse.of(memberInfo, customerInfo));
    }

    @PostMapping("/ticket-payment")
    public ResponseEntity<CustomerTicketPaymentResponse> purchaseTicket(
        @Authorization AuthorizationInfo authorizationInfo,
        @RequestBody @Valid CustomerTicketPaymentRequest request) {
        PaymentResult result = customerTicketPaymentService.purchase(
            new CustomerTicketPaymentCommand(
                request.studyCafeId(),
                authorizationInfo.id(),
                request.customerId(),
                request.ticketId(),
                request.paymentMethodType(),
                request.paymentMethodId()
            )
        );


        return ResponseEntity.ok(CustomerTicketPaymentResponse.of(result));
    }

}