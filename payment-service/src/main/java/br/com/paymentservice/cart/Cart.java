package br.com.paymentservice.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Long id;

    private String ownerEmail;

    private List<CartItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
