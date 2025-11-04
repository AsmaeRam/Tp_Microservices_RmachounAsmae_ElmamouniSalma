package com.example.recommendation_service.controller;

import com.example.recommendation_service.dto.RecommendationDTO;
import com.example.recommendation_service.model.Recommendation;
import com.example.recommendation_service.repo.RecommendationRepository;
import com.example.recommendation_service.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Recommendation> create(@Valid @RequestBody RecommendationDTO dto) {
        Recommendation saved = service.create(dto);
        return ResponseEntity.created(URI.create("/recommendations/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Recommendation>> list(@RequestParam(required = false) Long productId) {
        if (productId != null) return ResponseEntity.ok(service.listByProductId(productId));
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recommendation> update(@PathVariable Long id, @Valid @RequestBody RecommendationDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
