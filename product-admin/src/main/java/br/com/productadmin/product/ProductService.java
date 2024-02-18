package br.com.productadmin.product;

import br.com.productadmin.category.Category;
import br.com.productadmin.category.CategoryRepository;
import br.com.productadmin.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Product create(CreateProductRequest createProductRequest) {
        List<Category> categories = categoryRepository.findAllById(createProductRequest.categoriesIds());
        Product product = new Product(createProductRequest.name(), createProductRequest.description(), createProductRequest.price(), createProductRequest.quantity(), categories);

        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product update(Long id, UpdateProductRequest updateProductRequest) {
        List<Category> categories = categoryRepository.findAllById(updateProductRequest.categoriesId());
        Product product = findById(id);
        product.update(updateProductRequest.name(), updateProductRequest.description(), updateProductRequest.price(), categories);

        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
