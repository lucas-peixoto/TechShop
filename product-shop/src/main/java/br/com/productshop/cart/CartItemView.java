package br.com.productshop.cart;

import java.math.BigDecimal;

public record CartItemView(Long productId, Integer quantity, BigDecimal subtotal) {
    public CartItemView(CartItem cartItem) {
        this(cartItem.getProductId(), cartItem.getQuantity(), cartItem.getSubtotal());
    }
}