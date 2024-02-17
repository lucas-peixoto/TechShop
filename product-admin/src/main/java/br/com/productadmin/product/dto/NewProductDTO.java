package br.com.productadmin.product.dto;

import br.com.productadmin.product.Product;

import java.math.BigDecimal;

public record NewProductDTO(String name, String description, BigDecimal price, Double quantity) {

    public Product toModel() {
        return new Product(name, description, price, quantity);
    }
}
