package com.example.product_composite.client;

import com.example.product_composite.Dtos.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", path = "/products")
public interface ProductClient {

    @GetMapping("/{id}")
    Product get(@PathVariable("id") Long id);

    @PostMapping
    Product create(@RequestBody Product dto);

    @PutMapping("/{id}")
    Product update(@PathVariable("id") Long id, @RequestBody Product dto);
}
