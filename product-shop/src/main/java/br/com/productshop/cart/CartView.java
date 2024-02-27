package br.com.productshop.cart;

import java.util.List;

public record CartView(Long id, String ownerEmail, List<CartItemView> items) {
    public CartView(Cart cart) {
        this(cart.getId(), cart.getOwnerEmail(), cart.getItems().stream().map(CartItemView::new).toList());
    }
}