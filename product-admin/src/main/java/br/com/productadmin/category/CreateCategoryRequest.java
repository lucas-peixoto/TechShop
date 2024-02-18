package br.com.productadmin.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(@NotBlank String name, @NotBlank String description) {
}
