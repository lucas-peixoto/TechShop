package br.com.productadmin.category;

import br.com.productadmin.exception.NotFoundException;
import br.com.productadmin.exception.ValidationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;

    public CategoryService(CategoryRepository categoryRepository, CategoryValidator categoryValidator) {
        this.categoryRepository = categoryRepository;
        this.categoryValidator = categoryValidator;
    }

    public Category create(CreateCategoryRequest createCategoryRequest) {
        categoryValidator.validateForCreation(createCategoryRequest).throwIfInvalid();
        Category category = new Category(createCategoryRequest.name(), createCategoryRequest.description());

        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category update(Long id, UpdateCategoryRequest updateCategoryRequest) {
        categoryValidator.validateForUpdate(id, updateCategoryRequest).throwIfInvalid();
        Category category = findById(id);
        category.update(updateCategoryRequest.name(), updateCategoryRequest.description());
        categoryRepository.save(category);

        return category;
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
