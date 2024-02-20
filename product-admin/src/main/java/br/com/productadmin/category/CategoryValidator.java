package br.com.productadmin.category;

import br.com.productadmin.exception.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public CategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ValidationResult validateForCreation(CreateCategoryRequest createCategoryRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (categoryRepository.existsByName(createCategoryRequest.name())) {
            validationResult.addError("name", "Category name '%s' already exists".formatted(createCategoryRequest.name()));
        }

        return validationResult;
    }

    public ValidationResult validateForUpdate(Long id, UpdateCategoryRequest updateCategoryRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (categoryRepository.existsByNameAndIdNot(updateCategoryRequest.name(), id)) {
            validationResult.addError("name", "Category name '%s' already exists".formatted(updateCategoryRequest.name()));
        }

        return validationResult;
    }
}
