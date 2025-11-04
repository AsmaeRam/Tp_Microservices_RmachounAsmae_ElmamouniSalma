package com.example.product_composite.client;

import com.example.product_composite.Dtos.Recommendation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "recommendation-service", path = "/recommendations")
public interface RecommendationClient {

    @GetMapping
    List<Recommendation> listByProduct(@RequestParam("productId") Long productId);

    @PostMapping
    Recommendation create(@RequestBody Recommendation dto);
}
