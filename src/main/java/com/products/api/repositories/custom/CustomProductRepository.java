package com.products.api.repositories.custom;

import com.products.api.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomProductRepository {
    List<Product> findByTitleLike(String title);
}
