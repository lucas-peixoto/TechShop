package br.com.productshop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductAdminClient productAdminClient;

    public ProductService(ProductAdminClient productAdminClient) {
        this.productAdminClient = productAdminClient;
    }

    public Product getProductById(Long id) {
        return productAdminClient.getProductById(id);
    }

    public Page<Product> getProducts(Pageable pageable) {
        if (pageable.getSort().isSorted()) {
            return productAdminClient.getProducts(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        }

        return productAdminClient.getProducts(pageable.getPageNumber(), pageable.getPageSize());
    }

    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        if (pageable.getSort().isSorted()) {
            return productAdminClient.getProductsByCategory(categoryId, pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        }

        return productAdminClient.getProductsByCategory(categoryId, pageable.getPageNumber(), pageable.getPageSize());
    }

    public List<Product> getProductsByIds(List<Long> ids) {
        return productAdminClient.getProductsByIds(ids);
    }
}
