package com.products.api.services.impl;

import com.products.api.dtos.requests.ProductRequest;
import com.products.api.models.Product;
import com.products.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product createProduct(ProductRequest productRequest) {
    Product product = new Product(productRequest.getDetails(), productRequest.getTitle(), productRequest.getCategory());
    return productRepository.save(product);
  }

  @Override
  public List<Product> listAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductDetails(UUID productId) {
    return productRepository.findById(productId).get();
  }
}
