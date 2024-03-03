package br.com.paymentservice.cart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartShopClient cartShopClient;

    public CartService(CartShopClient cartShopClient) {
        this.cartShopClient = cartShopClient;
    }

    public List<Cart> getCarts() {
        return cartShopClient.getCarts();
    }
}
