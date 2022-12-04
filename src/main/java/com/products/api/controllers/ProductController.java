package com.products.api.controllers;

import com.products.api.models.Product;
import com.products.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController()
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public Product createProduct(@RequestBody @Valid Product product) {

    return productService.createProduct(product);
  }

  @GetMapping
  public List<Product> listProducts() {

    return productService.listAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProductDetails(@PathVariable UUID id) {

    return productService.getProductDetails(id);
  }
}