package com.jsl.shop_inn.common.util;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemRequest(
        @NotNull(message = "Item name is required")
        @NotEmpty(message = "Item name cannot be empty")
        @NotBlank(message = "Item name cannot be blank")
        String name,

        @Positive(message = "Item price cannot be negative")
        @NotNull(message = "Item price is required")
        BigDecimal price,

        @NotNull(message = "Item color is required")
        @NotEmpty(message = "Item color cannot be empty")
        @NotBlank(message = "Item color cannot be blank")
        String color,

        @NotNull(message = "Furniture dimensions are required")
        @NotEmpty(message = "Furniture dimensions cannot be empty")
        @NotBlank(message = "Furniture dimensions cannot be blank")
        String dimension,

        @NotNull(message = "Item category is required")
        @NotEmpty(message = "Item category cannot be empty")
        @NotBlank(message = "Item category cannot be blank")
        String category,

        @Positive(message = "Item quantity cannot be negative")
        @NotNull(message = "Item quantity is required")
        int quantity,

        String additional
) {
}
