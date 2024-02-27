package br.com.productshop.cart;

public record CartItemView(Long productId, Integer quantity) {
    public CartItemView(CartItem cartItem) {
        this(cartItem.getProductId(), cartItem.getQuantity());
    }
}