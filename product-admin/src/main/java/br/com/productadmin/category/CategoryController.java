package br.com.productadmin.category;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryView> create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        Category category = categoryService.create(createCategoryRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryView(category));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryView> findById(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);

        return ResponseEntity.ok(new CategoryView(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryView>> findAll(Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);

        return ResponseEntity.ok(categories.map(CategoryView::new));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryView> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryService.update(id, updateCategoryRequest);

        return ResponseEntity.ok(new CategoryView(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
