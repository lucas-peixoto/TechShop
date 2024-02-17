package br.com.productadmin.product.dto;

import br.com.productadmin.product.Product;

import java.math.BigDecimal;

public record UpdateProductDTO(Long id, String name, String description, BigDecimal price, Double quantity) {

}
