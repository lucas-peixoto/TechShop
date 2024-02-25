package br.com.productshop.product;

import br.com.productshop.category.CategoryView;

import java.math.BigDecimal;
import java.util.List;

public record ProductView(Long id, String name, String description, List<CategoryView> categories, BigDecimal price) {

    public ProductView(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategories().stream().map(CategoryView::new).toList(), product.getPrice());
    }
}
