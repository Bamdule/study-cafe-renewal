package io.spring.studycafe.applcation.paymentmethod.card;

public record CardInfo(
    Long id,
    String message,
    boolean success
) {
}
