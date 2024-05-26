package io.spring.studycafe.applcation.payment.adapter.card.adapter;

public record CardPaymentRequest(String orderCode, String itemName, Long itemPrice, String cardSecretKey) {
}
