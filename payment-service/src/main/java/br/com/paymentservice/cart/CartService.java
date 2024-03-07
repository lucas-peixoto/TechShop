package br.com.paymentservice.cart;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartShopClient cartShopClient;

    public CartService(CartShopClient cartShopClient) {
        this.cartShopClient = cartShopClient;
    }

    public Cart getCartById(Long id) {
        return cartShopClient.getCartId(id).orElseThrow(NotFoundException::new);
    }

    public BigDecimal getTotalCart(Long id) {
        return cartShopClient.getTotal(id);
    }

}
