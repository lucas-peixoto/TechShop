package br.com.productadmin.category;

public record CategoryView(Long id, String name, String description) {

    public CategoryView(Category category) {
        this(category.getId(), category.getName(), category.getDescription());
    }
}
