package com.example.product_composite.Dtos;

import java.util.List;

public record AggregateDTO(
        Product product,
        List<Review> reviews,
        List<Recommendation> recommendations
) {}

