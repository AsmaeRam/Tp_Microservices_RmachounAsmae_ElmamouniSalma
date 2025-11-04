package com.example.product_composite.Controllers;

import com.example.product_composite.service.CompositeService;
import com.example.product_composite.Dtos.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/product-composite")
public class CompositeController {

    private final CompositeService service;

    public CompositeController(CompositeService service) {
        this.service = service;
    }

    // ---- READ (agrégat)
    @GetMapping("/{productId}")
    public ResponseEntity<AggregateDTO> get(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getAggregate(productId));
    }

    // ---- WRITE (passe-plat) pour compter 7a
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product dto) {
        var saved = service.createProduct(dto);
        return ResponseEntity.created(URI.create("/product-composite/" + saved.getId())).body(saved);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product dto) {
        return ResponseEntity.ok(service.updateProduct(id, dto));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@RequestBody Review dto) {
        var saved = service.createReview(dto);
        return ResponseEntity.created(URI.create("/product-composite/" + saved.getProductId())).body(saved);

    }

    @PostMapping("/recommendations")
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody Recommendation dto) {
        var saved = service.createRecommendation(dto);
        return ResponseEntity.created(URI.create("/product-composite/" + saved.getProductId())).body(saved);

    }
}
