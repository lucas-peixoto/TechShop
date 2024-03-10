package br.com.productshop.cart;

import br.com.productshop.exception.ValidationResult;
import br.com.productshop.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CartValidator {

    public ValidationResult validateAddItems(Cart cart, List<CartItemRequest> cartItemRequest, List<Product> productsByIds) {
        ValidationResult validationResult = new ValidationResult();

        cartItemRequest.stream().map(CartItemRequest::productId).forEach(productId -> {
            if (productsByIds.stream().noneMatch(product -> product.getId().equals(productId))) {
                validationResult.addError("productId", "Product %s not found".formatted(productId));
            }
        });

        return validationResult;
    }

    public ValidationResult validateRemoveItems(Cart cart, List<CartItemRequest> cartItemRequests) {
        ValidationResult validationResult = new ValidationResult();

        Map<Long, Integer> productsQuantityMap = cart.getItems().stream().collect(Collectors.toMap(CartItem::getProductId, CartItem::getQuantity));

        cartItemRequests.forEach(cartItemRequest -> {
            if (productsQuantityMap.get(cartItemRequest.productId()) == null) {
                validationResult.addError("productId", "Product %s not found in cart".formatted(cartItemRequest.productId()));
            } else if (productsQuantityMap.get(cartItemRequest.productId()) < cartItemRequest.quantity()) {
                validationResult.addError("quantity", "Quantity of product %s is less than requested".formatted(cartItemRequest.productId()));
            }
        });

        return validationResult;
    }
}
