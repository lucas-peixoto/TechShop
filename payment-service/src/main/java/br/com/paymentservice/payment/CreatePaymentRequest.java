package br.com.paymentservice.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePaymentRequest(@NotNull Long cartId, @NotNull @DecimalMin("0.1") BigDecimal amount,
                                   @NotNull PaymentMethod paymentMethod) {
}
