package br.com.paymentservice.cart;

public class CartItem {

    private Long id;

    private Long productId;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
