package io.spring.studycafe.applcation.paymentmethod.card;

public record CardRegistrationResult(
    String message,
    boolean success
) {
}
