package br.com.productadmin.product;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductView> save(@RequestBody @Valid CreateProductRequest newProductRequest) {
        Product product = productService.create(newProductRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductView(product));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductView> findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);

        return ResponseEntity.ok(new ProductView(product));
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductView>> findAll(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);

        return ResponseEntity.ok(products.map(ProductView::new));
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<Page<ProductView>> findByCategory(@PathVariable("categoryId") Long categoryId, Pageable pageable) {
        Page<Product> products = productService.findByCategory(categoryId, pageable);

        return ResponseEntity.ok(products.map(ProductView::new));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductView> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        Product product = productService.update(id, updateProductRequest);

        return ResponseEntity.ok(new ProductView(product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
