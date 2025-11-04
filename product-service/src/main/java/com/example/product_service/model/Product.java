package com.example.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0.0")   // non négatif
    @DecimalMax(value = "100.0") // ≤ 100 kg
    private BigDecimal weight;   // en kilogrammes

    private String description;

    public Product() {}

    public Product(Long id, String name, BigDecimal weight, String description) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
