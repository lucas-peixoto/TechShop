package br.com.productshop.cart;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public ResponseEntity<CartView> addItems(@Valid @RequestBody List<CartItemRequest> cartItemRequests) {
        Cart cart = cartService.addItems(cartItemRequests);
        return ResponseEntity.ok(new CartView(cart));
    }

    @PostMapping("/cart/remove")
    public ResponseEntity<CartView> removeItems(@Valid @RequestBody List<CartItemRequest> cartItemRequests) {
        Cart cart = cartService.removeItems(cartItemRequests);
        return ResponseEntity.ok(new CartView(cart));
    }
}
