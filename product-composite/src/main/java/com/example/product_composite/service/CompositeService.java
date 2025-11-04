package com.example.product_composite.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;	

import com.example.product_composite.client.ProductClient;
import com.example.product_composite.client.RecommendationClient;
import com.example.product_composite.client.ReviewClient;
import com.example.product_composite.Dtos.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompositeService {

    private final ProductClient productClient;
    private final ReviewClient reviewClient;
    private final RecommendationClient recommendationClient;

    private final Counter postPutCounter;
    private final Counter getCounter;

    public CompositeService(ProductClient productClient,
                            ReviewClient reviewClient,
                            RecommendationClient recommendationClient,
                            MeterRegistry registry) {
        this.productClient = productClient;
        this.reviewClient = reviewClient;
        this.recommendationClient = recommendationClient;
        this.postPutCounter = Counter.builder("composite.requests.write") // 7a
                .description("POST/PUT requests received by product-composite")
                .register(registry);
        this.getCounter = Counter.builder("composite.requests.read")     // 7b
                .description("GET requests received by product-composite")
                .register(registry);
    }

    public AggregateDTO getAggregate(Long productId) {
        getCounter.increment();
        Product p = productClient.get(productId);
        List<Review> reviews = reviewClient.listByProduct(productId);
        List<Recommendation> recos = recommendationClient.listByProduct(productId);
        return new AggregateDTO(p, reviews, recos);
    }

    public Product createProduct(Product dto) {
        postPutCounter.increment();
        return productClient.create(dto);
    }

    public Product updateProduct(Long id, Product dto) {
        postPutCounter.increment();
        return productClient.update(id, dto);
    }

    public Review createReview(Review dto) {
        postPutCounter.increment();
        return reviewClient.create(dto);
    }

    public Recommendation createRecommendation(Recommendation dto) {
        postPutCounter.increment();
        return recommendationClient.create(dto);
    }
    public AggregateDTO fallbackGetAggregate(Long productId, Throwable t) {
        System.out.println(" Circuit Breaker activé : " + t.getMessage());
        Product fallbackProduct = new Product(productId, "N/A", "Service indisponible", 0.0);
        return new AggregateDTO(fallbackProduct, List.of(), List.of());
    }


}
