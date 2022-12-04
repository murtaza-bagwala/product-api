package com.products.api.services;

import com.products.api.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
  Product createProduct(Product product);

  List<Product> listAllProducts();

  Product getProductDetails(UUID productId);
}
