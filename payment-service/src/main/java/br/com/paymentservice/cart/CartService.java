package br.com.paymentservice.cart;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartShopClient cartShopClient;

    public CartService(CartShopClient cartShopClient) {
        this.cartShopClient = cartShopClient;
    }

}
