package io.spring.studycafe.applcation.payment;

public record PaymentResult(
    Long paymentId,
    String itemName,
    Long itemPrice,
    String message,
    String resultType,
    boolean success
) {
}