package com.example.recommendation_service.dto;



import jakarta.validation.constraints.*;

public record RecommendationDTO(
        Long id,
        @NotNull Long productId,
        @NotBlank String author,
        @NotNull @Min(0) @Max(100) Integer rate,
        String content
) {}

