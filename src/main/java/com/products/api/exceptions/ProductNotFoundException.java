package com.products.api.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(UUID id) {
    super(String.format("Product with Id %s not found", id));
  }
}
