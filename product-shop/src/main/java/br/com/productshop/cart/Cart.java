package br.com.productshop.cart;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerEmail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Long getId() {
        return id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItems(List<CartItem> items) {
        items.forEach(this::addItem);
    }

    private void addItem(CartItem item) {
        Optional<CartItem> existingItem = items.stream()
                .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }
    }

    public void removeItems(List<CartItem> items) {
        items.forEach(this::removeItem);
    }

    public void removeItem(CartItem item) {
        Optional<CartItem> existingItem = items.stream()
                .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() - item.getQuantity();
            if (newQuantity > 0) {
                existingItem.get().setQuantity(newQuantity);
            } else {
                items.remove(existingItem.get());
            }
        }
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
