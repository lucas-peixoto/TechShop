package br.com.productshop.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemRequest(@NotNull Long productId, @NotNull @Min(1) Integer quantity) {
    public CartItem toModel() {
        return new CartItem(productId(), quantity());
    }
}
