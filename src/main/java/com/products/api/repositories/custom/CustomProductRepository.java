package com.products.api.repositories.custom;

import com.products.api.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomProductRepository {
    Optional<Product> findByTitle(String title);
}
