package br.com.productadmin.inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping("/inventory/{productId}/increment/{quantity}")
    public ResponseEntity<Void> incrementInventory(@PathVariable Long productId, @PathVariable Integer quantity) {
        inventoryService.incrementInventory(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/inventory/{productId}/decrement/{quantity}")
    public ResponseEntity<Void> decrementInventory(@PathVariable Long productId, @PathVariable Integer quantity) {
        inventoryService.decrementInventory(productId, quantity);
        return ResponseEntity.ok().build();
    }
}
