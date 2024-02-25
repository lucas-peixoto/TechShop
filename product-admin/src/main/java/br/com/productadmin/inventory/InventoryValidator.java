package br.com.productadmin.inventory;

import br.com.productadmin.exception.ValidationResult;
import br.com.productadmin.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class InventoryValidator {

    private final ProductRepository productRepository;

    public InventoryValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ValidationResult validateForIncrement(Long productId, int quantity) {
        ValidationResult validationResult = new ValidationResult();

        if (!productRepository.existsById(productId)) {
            validationResult.addError("productId", "Product with id '%s' not found".formatted(productId));
        }

        return validationResult;
    }

    public ValidationResult validateForDecrement(Long productId, int quantity) {
        ValidationResult validationResult = new ValidationResult();

        if (!productRepository.existsById(productId)) {
            validationResult.addError("productId", "Product with id '%s' not found".formatted(productId));
        }

        if (!productRepository.existsByIdAndInventoryGreaterThanEqual(productId, quantity)) {
            validationResult.addError("quantity", "Insufficient inventory");
        }

        return validationResult;
    }
}
