package com.backend.inventory.repository;

import com.backend.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String name);

    boolean existsBySku(String sku);

}
