package com.example.review_service.controllers;

import com.example.review_service.Dto.ReviewDTO;
import com.example.review_service.model.Review;
import com.example.review_service.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Review> create(@Valid @RequestBody ReviewDTO dto) {
        Review saved = service.create(dto);
        return ResponseEntity.created(URI.create("/reviews/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Review>> list(@RequestParam(required = false) Long productId) {
        if (productId != null) return ResponseEntity.ok(service.listByProductId(productId));
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @Valid @RequestBody ReviewDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
