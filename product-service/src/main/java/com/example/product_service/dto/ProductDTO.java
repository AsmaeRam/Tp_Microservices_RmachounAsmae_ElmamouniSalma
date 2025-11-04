package com.example.product_service.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        @NotBlank String name,
        @NotNull @DecimalMin("0.0") @DecimalMax("100.0") BigDecimal weight,
        String description
) {}
