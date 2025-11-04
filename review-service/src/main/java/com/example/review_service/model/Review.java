package com.example.review_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;

    @NotBlank
    private String author;

    @NotBlank
    private String content;

    public Review() {}

    public Review(Long id, Long productId, String author, String content) {
        this.id = id;
        this.productId = productId;
        this.author = author;
        this.content = content;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
