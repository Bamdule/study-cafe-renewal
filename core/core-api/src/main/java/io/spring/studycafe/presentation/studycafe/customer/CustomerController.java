package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.member.MemberInfo;
import io.spring.studycafe.applcation.member.MemberSearchService;
import io.spring.studycafe.applcation.studycafe.customer.CustomerInfo;
import io.spring.studycafe.applcation.studycafe.customer.CustomerRegistrationCommand;
import io.spring.studycafe.applcation.studycafe.customer.CustomerService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스터디 카페 회원")
@RequestMapping(value = "/api/v1/study-cafe-customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final MemberSearchService memberSearchService;

    public CustomerController(CustomerService customerService, MemberSearchService memberSearchService) {
        this.customerService = customerService;
        this.memberSearchService = memberSearchService;
    }

    @GetMapping
    public ResponseEntity<CustomerAggregateResponse> findCustomer(
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo,
        @RequestParam(value = "studyCafeId") Long studyCafeId
    ) {
        MemberInfo memberInfo = memberSearchService.getById(authorizationInfo.id());
        CustomerInfo customerInfo = customerService.find(authorizationInfo.id(), studyCafeId);

        return ResponseEntity.ok(CustomerAggregateResponse.of(memberInfo, customerInfo));
    }

    @PostMapping
    public ResponseEntity<CustomerAggregateResponse> registerCustomer(
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo,
        @RequestParam(value = "studyCafeId") Long studyCafeId
    ) {
        MemberInfo memberInfo = memberSearchService.getById(authorizationInfo.id());
        CustomerInfo customerInfo = customerService.register(new CustomerRegistrationCommand(authorizationInfo.id(), studyCafeId));

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerAggregateResponse.of(memberInfo, customerInfo));
    }
}
