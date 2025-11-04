package com.example.review_service.service;

import com.example.review_service.Dto.ReviewDTO;
import com.example.review_service.model.Review;
import com.example.review_service.repo.ReviewRepository;
import com.example.review_service.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public Review create(ReviewDTO dto) {
        Review r = new Review(null, dto.productId(), dto.author(), dto.content());
        return repo.save(r);
    }

    public Review getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    }

    public List<Review> listAll() {
        return repo.findAll();
    }

    public List<Review> listByProductId(Long productId) {
        return repo.findByProductId(productId);
    }

    public Review update(Long id, ReviewDTO dto) {
        Review r = getById(id);
        r.setProductId(dto.productId());
        r.setAuthor(dto.author());
        r.setContent(dto.content());
        return repo.save(r);
    }

    public void delete(Long id) {
        Review r = getById(id);
        repo.delete(r);
    }
}
