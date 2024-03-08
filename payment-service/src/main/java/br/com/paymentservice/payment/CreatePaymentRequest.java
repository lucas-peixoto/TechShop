package br.com.paymentservice.payment;

import jakarta.validation.constraints.NotNull;

public record CreatePaymentRequest(@NotNull Long cartId, @NotNull PaymentMethod paymentMethod) {
}
