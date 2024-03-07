package br.com.paymentservice.payment;

import br.com.paymentservice.paymentMethod.PaymentMethod;
import jakarta.validation.constraints.NotNull;

public record CreatePaymentRequest(@NotNull Long cartId, @NotNull PaymentMethod paymentMethod) {
}
