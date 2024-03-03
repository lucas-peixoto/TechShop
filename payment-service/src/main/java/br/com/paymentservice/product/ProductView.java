package br.com.paymentservice.product;


import br.com.paymentservice.category.CategoryView;

import java.math.BigDecimal;
import java.util.List;

public record ProductView(Long id, String name, String description, List<CategoryView> categories, BigDecimal price) {

    public ProductView(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategories().stream().map(CategoryView::new).toList(), product.getPrice());
    }
}
