package com.example.product_composite.client;

import com.example.product_composite.Dtos.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "review-service", path = "/reviews")
public interface ReviewClient {

    @GetMapping
    List<Review> listByProduct(@RequestParam("productId") Long productId);

    @PostMapping
    Review create(@RequestBody Review dto);
}
