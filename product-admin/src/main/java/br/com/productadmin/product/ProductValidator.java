package br.com.productadmin.product;

import br.com.productadmin.category.CategoryRepository;
import br.com.productadmin.exception.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductValidator {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductValidator(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public ValidationResult validateForCreation(CreateProductRequest createProductRequest) {
        Map<String, String> errors = new HashMap<>();

        if (productRepository.existsByName(createProductRequest.name())) {
            errors.put("name", "Product name '%s' already exists".formatted(createProductRequest.name()));
        }

        validateProductCategories(createProductRequest.categoriesIds(), errors);

        return errors.isEmpty() ? new ValidationResult.Success() : new ValidationResult.FieldErrors(errors);
    }

    public ValidationResult validateForUpdate(Long id, UpdateProductRequest updateProductRequest) {
        Map<String, String> errors = new HashMap<>();

        if (productRepository.existsByNameAndIdNot(updateProductRequest.name(), id)) {
            errors.put("name", "Product name '%s' already exists".formatted(updateProductRequest.name()));
        }

        validateProductCategories(updateProductRequest.categoriesId(), errors);

        return errors.isEmpty() ? new ValidationResult.Success() : new ValidationResult.FieldErrors(errors);
    }

    private void validateProductCategories(List<Long> categoriesIds, Map<String, String> errors) {
        if (!categoryRepository.existsAllByIdIn(categoriesIds)) {
            errors.put("categories", "Categories not found");
        }

        Set<Long> categoriesIdsSet = new HashSet<>(categoriesIds);
        if (categoriesIdsSet.size() != categoriesIds.size()) {
            errors.put("categories", "Repeated categories");
        }
    }
}
