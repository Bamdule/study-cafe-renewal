package io.spring.studycafe.applcation.payment.card.adapter;

public record CardPaymentRequest(String orderCode, String itemName, Long itemPrice, String cardSecretKey) {
}
