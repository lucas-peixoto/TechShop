package br.com.productadmin.product.dto;

import br.com.productadmin.product.Product;

import java.math.BigDecimal;

public record ProductView(Long id, String name, String description, BigDecimal price, Double quantity) {

    public ProductView(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
}
