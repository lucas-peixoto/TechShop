package br.com.paymentservice.product;


import br.com.paymentservice.category.Category;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Product {

    private Long id;
    private String name;
    private String description;
    private List<Category> categories;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
