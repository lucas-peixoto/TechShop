package br.com.productshop.productListing;

import br.com.productshop.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductListingController {

    private final ProductService productService;

    public ProductListingController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductView>> getAllProducts(Pageable pageable) {
        Page<Product> products = productService.getProducts(pageable);
        return ResponseEntity.ok(products.map(ProductView::new));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductView> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(new ProductView(product));
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<Page<ProductView>> getProductsByCategory(@PathVariable Long categoryId, Pageable pageable) {
        Page<Product> products = productService.getProductsByCategory(categoryId, pageable);
        return ResponseEntity.ok(products.map(ProductView::new));
    }
}
