package br.com.productadmin.category;

import br.com.productadmin.exception.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public CategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ValidationResult validateForCreation(CreateCategoryRequest createCategoryRequest) {
        Map<String, String> errors = new HashMap<>();

        if (categoryRepository.existsByName(createCategoryRequest.name())) {
            errors.put("name", "Category name '%s' already exists".formatted(createCategoryRequest.name()));
        }

        return errors.isEmpty() ? new ValidationResult.Success() : new ValidationResult.FieldErrors(errors);
    }

    public ValidationResult validateForUpdate(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Map<String, String> errors = new HashMap<>();

        if (categoryRepository.existsByNameAndIdNot(updateCategoryRequest.name(), id)) {
            errors.put("name", "Category name '%s' already exists".formatted(updateCategoryRequest.name()));
        }

        return errors.isEmpty() ? new ValidationResult.Success() : new ValidationResult.FieldErrors(errors);
    }
}
