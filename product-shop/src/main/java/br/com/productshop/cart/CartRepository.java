package br.com.productshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByOwnerEmail(String ownerEmail);
    List<CartItem> findByCart(Cart cart);
}