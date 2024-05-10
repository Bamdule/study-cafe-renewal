package io.spring.studycafe.presentation.studycafe.customer;

import io.spring.studycafe.applcation.member.MemberInfo;
import io.spring.studycafe.applcation.studycafe.customer.CustomerInfo;
import io.spring.studycafe.presentation.member.MemberResponse;

public record CustomerAggregateResponse(
    MemberResponse member,
    CustomerResponse customer
) {

    public static CustomerAggregateResponse of(MemberInfo memberInfo, CustomerInfo customerInfo) {
        return new CustomerAggregateResponse(MemberResponse.of(memberInfo), CustomerResponse.of(customerInfo));
    }
}
