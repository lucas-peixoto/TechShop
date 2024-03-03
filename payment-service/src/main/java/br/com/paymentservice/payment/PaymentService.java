package br.com.paymentservice.payment;

import br.com.paymentservice.cart.CartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final CartService cartService;

    public PaymentService(CartService cartService) {
        this.cartService = cartService;
    }

    private BigDecimal getAmountForProducts() {
        return new BigDecimal(0.0);
    }
}
