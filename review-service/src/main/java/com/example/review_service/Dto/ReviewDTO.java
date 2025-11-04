package com.example.review_service.Dto;

import jakarta.validation.constraints.*;

public record ReviewDTO(
        Long id,
        @NotNull Long productId,
        @NotBlank String author,
        @NotBlank String content
) {}
