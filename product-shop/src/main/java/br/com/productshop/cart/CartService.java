package br.com.productshop.cart;

import br.com.productshop.user.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final LoggedUser loggedUser;

    public CartService(CartRepository cartRepository, LoggedUser loggedUser) {
        this.cartRepository = cartRepository;
        this.loggedUser = loggedUser;
    }

    public Cart addItems(List<CartItemRequest> cartItemRequests) {
        String ownerEmail = loggedUser.getEmail();
        Cart cart = cartRepository.findByOwnerEmail(ownerEmail).orElseGet(() -> new Cart(ownerEmail));
        cart.addItems(cartItemRequests.stream().map(CartItemRequest::toModel).toList());

        return cartRepository.save(cart);
    }

    public Cart removeItems(List<CartItemRequest> cartItemRequests) {
        String ownerEmail = loggedUser.getEmail();
        Cart cart = cartRepository.findByOwnerEmail(ownerEmail).orElseGet(() -> new Cart(ownerEmail));
        cart.removeItems(cartItemRequests.stream().map(CartItemRequest::toModel).toList());

        return cartRepository.save(cart);
    }
}
