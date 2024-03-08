package br.com.productshop.cart;

import br.com.productshop.exception.ValidationResult;
import br.com.productshop.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartValidator {

    public ValidationResult validateAddItems(Cart cart, List<CartItemRequest> cartItemRequest, List<Product> productsByIds) {
        ValidationResult validationResult = new ValidationResult();

        if (cartItemRequest.size() != productsByIds.size()) {
            validationResult.addError("quantity", "Some products were not found");
        }

        cartItemRequest.stream().map(CartItemRequest::productId).forEach(productId -> {
            if (productsByIds.stream().noneMatch(product -> product.getId().equals(productId))) {
                validationResult.addError("productId", "Product %s not found".formatted(productId));
            }
        });

        return validationResult;
    }
}
