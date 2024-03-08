package br.com.productshop.cart;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartView> findById(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        return ResponseEntity.ok(new CartView(cart));
    }

    @GetMapping("/cart/total/{id}")
    public ResponseEntity<BigDecimal> getTotal(@PathVariable Long id) {
        BigDecimal totalCart = cartService.getTotalCart(id);
        return ResponseEntity.ok(totalCart);
    }
}
