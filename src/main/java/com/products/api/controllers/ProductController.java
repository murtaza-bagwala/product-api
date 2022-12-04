package com.products.api.controllers;

import com.products.api.dtos.requests.ProductRequest;
import com.products.api.models.Product;
import com.products.api.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController()
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public Product createProduct(@RequestBody ProductRequest productRequest) {

    return productService.createProduct(productRequest);
  }

  @GetMapping
  public List<Product> listProducts(@RequestBody ProductRequest productRequest) {

    return productService.listAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProductDetails(@PathVariable UUID id) {

    return productService.getProductDetails(id);
  }
}