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
        ValidationResult validationResult = new ValidationResult();

        if (productRepository.existsByName(createProductRequest.name())) {
            validationResult.addError("name", "Product name '%s' already exists".formatted(createProductRequest.name()));
        }

        validateProductCategories(createProductRequest.categoriesIds(), validationResult);

        return validationResult;
    }

    public ValidationResult validateForUpdate(Long id, UpdateProductRequest updateProductRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (productRepository.existsByNameAndIdNot(updateProductRequest.name(), id)) {
            validationResult.addError("name", "Product name '%s' already exists".formatted(updateProductRequest.name()));
        }

        validateProductCategories(updateProductRequest.categoriesIds(), validationResult);

        return validationResult;
    }

    private void validateProductCategories(List<Long> categoriesIds, ValidationResult validationResult) {
        if (!categoryRepository.existsAllByIdIn(categoriesIds)) {
            validationResult.addError("categoriesIds", "Categories not found");
        }

        Set<Long> categoriesIdsSet = new HashSet<>(categoriesIds);
        if (categoriesIdsSet.size() != categoriesIds.size()) {
            validationResult.addError("categoriesIds", "Repeated categories");
        }
    }
}
