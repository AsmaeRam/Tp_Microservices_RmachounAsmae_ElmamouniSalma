package com.example.product_service.service;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.model.Product;
import com.example.product_service.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // === CREATE ===
    public Product create(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.name());
        p.setWeight(dto.weight());
        p.setDescription(dto.description());
        return repo.save(p);
    }

    // === READ (by id) ===
    public Product getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable avec ID : " + id));
    }


    // === READ (all) ===
    public List<Product> listAll() {
        return repo.findAll();
    }

    // === UPDATE ===
    public Product update(Long id, ProductDTO dto) {
        Product p = getById(id);
        p.setName(dto.name());
        p.setWeight(dto.weight());
        p.setDescription(dto.description());
        return repo.save(p);
    }

    // === DELETE ===
    public void delete(Long id) {
        Product p = getById(id);
        repo.delete(p);
    }
}
