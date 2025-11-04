package com.example.product_composite.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double weight;

    // ✅ Constructeur complet
    public Product(Long id, String name, String description, Double weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    // ✅ Constructeur vide (obligatoire pour Jackson)
    public Product() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
}
