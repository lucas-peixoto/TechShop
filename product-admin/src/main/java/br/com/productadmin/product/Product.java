package br.com.productadmin.product;

import br.com.productadmin.product.dto.UpdateProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, Double quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public Double getQuantity() {
        return quantity;
    }

    public void update(UpdateProductDTO updateProductDTO) {
        this.name = updateProductDTO.name();
        this.description = updateProductDTO.description();
        this.price = updateProductDTO.price();
        this.quantity = updateProductDTO.quantity();
    }
}
