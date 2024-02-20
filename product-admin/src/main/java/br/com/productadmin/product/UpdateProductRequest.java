package br.com.productadmin.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProductRequest(@NotBlank String name, @NotBlank String description,
                                   @NotNull @DecimalMin("0.01") @Digits(integer = 8, fraction = 2) BigDecimal price,
                                   @NotEmpty List<Long> categoriesIds) {
}
