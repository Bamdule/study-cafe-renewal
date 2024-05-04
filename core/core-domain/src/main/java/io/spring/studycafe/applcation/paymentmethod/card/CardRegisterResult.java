package io.spring.studycafe.applcation.paymentmethod.card;

public record CardRegisterResult(
    String message,
    boolean success
) {
}
