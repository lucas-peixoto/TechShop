package br.com.productadmin.product;

import br.com.productadmin.product.dto.NewProductDTO;
import br.com.productadmin.product.dto.ProductView;
import br.com.productadmin.product.dto.UpdateProductDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductView createProduct(NewProductDTO newProductDTO) {
        Product product = newProductDTO.toModel();
        product = productRepository.save(product);

        return new ProductView(product);
    }

    public ProductView findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return new ProductView(product);
    }

    public List<ProductView> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductView::new).toList();
    }

    @Transactional
    public ProductView updateProduct(Long id, UpdateProductDTO updateProductDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.update(updateProductDTO);
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
