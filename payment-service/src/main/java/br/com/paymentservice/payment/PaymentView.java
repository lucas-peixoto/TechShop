package br.com.paymentservice.payment;

import java.math.BigDecimal;

public record PaymentView(Long cartId, BigDecimal amount, PaymentMethod paymentMethod) {
    public PaymentView(Payment payment) {this(payment.getCartId(), payment.getAmount(), payment.getPaymentMethod());}
}
