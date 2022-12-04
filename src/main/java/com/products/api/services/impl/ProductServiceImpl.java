package com.products.api.services.impl;

import com.products.api.exceptions.NoDataFoundException;
import com.products.api.exceptions.ProductNotFoundException;
import com.products.api.models.Product;
import com.products.api.repositories.ProductRepository;
import com.products.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public List<Product> listAllProducts() {
    List<Product> products = productRepository.findAll();
    if (products.isEmpty()) {
      throw new NoDataFoundException();
    }
    return products;
  }

  @Override
  public Product getProductDetails(UUID productId) {
    return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
  }
}
