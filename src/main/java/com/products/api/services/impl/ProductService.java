package com.products.api.services.impl;

import com.products.api.dtos.requests.ProductRequest;
import com.products.api.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
  Product createProduct(ProductRequest productRequest);

  List<Product> listAllProducts();

  Product getProductDetails(UUID productId);
}
