package br.com.productadmin.inventory;

import br.com.productadmin.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryValidator inventoryValidator;
    private final ProductRepository productRepository;

    public InventoryService(InventoryValidator inventoryValidator, ProductRepository productRepository) {
        this.inventoryValidator = inventoryValidator;
        this.productRepository = productRepository;
    }

    public void incrementInventory(Long productId, int quantity) {
        inventoryValidator.validateForIncrement(productId, quantity).throwIfInvalid();
        productRepository.incrementInventory(productId, quantity);
    }

    public void decrementInventory(Long productId, int quantity) {
        inventoryValidator.validateForDecrement(productId, quantity).throwIfInvalid();
        productRepository.decrementInventory(productId, quantity);
    }
}
