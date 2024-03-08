package br.com.productshop.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartView(Long id, String ownerEmail, List<CartItemView> items, BigDecimal total) {
    public CartView(Cart cart) {
        this(cart.getId(), cart.getOwnerEmail(), cart.getItems().stream().map(CartItemView::new).toList(), cart.getTotal());
    }
}