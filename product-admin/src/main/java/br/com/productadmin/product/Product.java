package br.com.productadmin.product;

import br.com.productadmin.category.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> category;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, int inventory, List<Category> category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(category);
    }

    public void update(String name, String description, BigDecimal price, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = categories;
    }
}
