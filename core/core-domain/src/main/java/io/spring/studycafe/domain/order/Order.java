package io.spring.studycafe.domain.order;

import lombok.Getter;

@Getter
public class Order {
    private Long studyCafeId;
    private Long memberId;
    private Long customerId;
    private Long paymentMethodId;
    private String itemName;
    private Long itemPrice;
    private String orderCode;


    public Order(Long studyCafeId, Long memberId, Long customerId, Long paymentMethodId, String itemName, Long itemPrice, String orderCode) {
        this.studyCafeId = studyCafeId;
        this.memberId = memberId;
        this.customerId = customerId;
        this.paymentMethodId = paymentMethodId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderCode = orderCode;
    }
}
