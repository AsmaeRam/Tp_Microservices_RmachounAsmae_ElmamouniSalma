package com.example.recommendation_service.service;


import com.example.recommendation_service.dto.RecommendationDTO;
import com.example.recommendation_service.model.Recommendation;
import com.example.recommendation_service.repo.RecommendationRepository;
import com.example.recommendation_service.exception.ErrorHandler;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecommendationService {

    private final RecommendationRepository repo;

    public RecommendationService(RecommendationRepository repo) {
        this.repo = repo;
    }

    public Recommendation create(RecommendationDTO dto) {
        Recommendation r = new Recommendation(
                null, dto.productId(), dto.author(), dto.rate(), dto.content()
        );
        return repo.save(r);
    }

    public Recommendation getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ErrorHandler.ResourceNotFoundException("Recommendation not found with id " + id));
    }

    public List<Recommendation> listAll() {
        return repo.findAll();
    }

    public List<Recommendation> listByProductId(Long productId) {
        return repo.findByProductId(productId);
    }

    public Recommendation update(Long id, RecommendationDTO dto) {
        Recommendation r = getById(id);
        r.setProductId(dto.productId());
        r.setAuthor(dto.author());
        r.setRate(dto.rate());
        r.setContent(dto.content());
        return repo.save(r);
    }

    public void delete(Long id) {
        Recommendation r = getById(id);
        repo.delete(r);
    }
}
