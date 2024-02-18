package br.com.productadmin.category;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(@NotBlank String name, @NotBlank String description) {
}
