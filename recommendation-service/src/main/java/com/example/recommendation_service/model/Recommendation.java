package com.example.recommendation_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;

    @NotBlank
    private String author;

    @NotNull
    @Min(0)   // pourcentage 0..100
    @Max(100)
    private Integer rate;

    private String content;

    public Recommendation() {}

    public Recommendation(Long id, Long productId, String author, Integer rate, String content) {
        this.id = id;
        this.productId = productId;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Integer getRate() { return rate; }
    public void setRate(Integer rate) { this.rate = rate; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
