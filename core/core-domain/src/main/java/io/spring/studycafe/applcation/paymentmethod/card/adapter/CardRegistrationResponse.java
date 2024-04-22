package io.spring.studycafe.applcation.paymentmethod.card.adapter;

public record CardRegistrationResponse(String cardSecretKey, String message, boolean success) {
}
