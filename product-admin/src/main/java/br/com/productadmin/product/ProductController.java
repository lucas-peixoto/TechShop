package br.com.productadmin.product;

import br.com.productadmin.product.dto.NewProductDTO;
import br.com.productadmin.product.dto.ProductView;
import br.com.productadmin.product.dto.UpdateProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductView> save(@RequestBody NewProductDTO newProductDTO) {
        ProductView product = productService.createProduct(newProductDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.id()).toUri();

        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductView> findById(@PathVariable("id") Long id) {
        ProductView product = productService.findProductById(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductView>> findAll() {
        List<ProductView> products = productService.findAllProducts();

        return ResponseEntity.ok(products);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductView> update(@PathVariable("id") Long id, @RequestBody UpdateProductDTO updateProductDTO) {
        ProductView product = productService.updateProduct(id, updateProductDTO);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
