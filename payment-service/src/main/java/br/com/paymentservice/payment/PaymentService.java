package br.com.paymentservice.payment;

import br.com.paymentservice.cart.Cart;
import br.com.paymentservice.cart.CartService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CartService cartService;

    public PaymentService(PaymentRepository paymentRepository, CartService cartService) {
        this.paymentRepository = paymentRepository;
        this.cartService = cartService;
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Payment create(CreatePaymentRequest createPaymentRequest) {
        Cart cart = cartService.getCartById(createPaymentRequest.cartId());
        BigDecimal total = cartService.getTotalCart(cart.getId());

        return paymentRepository.save(new Payment(cart.getId(), total, createPaymentRequest.paymentMethod()));
    }
}
