package br.com.productadmin.product;

import br.com.productadmin.category.CategoryView;

import java.math.BigDecimal;
import java.util.List;

public record ProductView(Long id, String name, String description, List<CategoryView> category, BigDecimal price,
                          int inventory) {

    public ProductView(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategories().stream().map(CategoryView::new).toList(), product.getPrice(), product.getInventory());
    }
}
