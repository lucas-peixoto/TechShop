package br.com.paymentservice.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductAdminClient productAdminClient;

    public ProductService(ProductAdminClient productAdminClient) {
        this.productAdminClient = productAdminClient;
    }

    public Product getProductById(Long id) {
        return productAdminClient.getProductById(id);
    }
}
