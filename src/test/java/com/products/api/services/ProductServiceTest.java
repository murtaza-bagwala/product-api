package com.products.api.services;

import com.products.api.ProductApiApplication;
import com.products.api.exceptions.NoDataFoundException;
import com.products.api.exceptions.ProductNotFoundException;
import com.products.api.models.Product;
import com.products.api.repositories.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductApiApplication.class)
public class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @BeforeEach
  public void setup() {
    productRepository.deleteAll();
  }

  @Test
  @DisplayName("Creates new Product")
  public void testCreateProduct() throws Exception {
    Product product = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    Product response = productService.createProduct(product);
    assertNotNull(response.getId());
    assertEquals(response.getDetails(), product.getDetails());
  }

  @Test
  @DisplayName("Throws an error if contains null for non nullable columns")
  public void testCreateProductWithError() throws Exception {
    Product product = new Product("A healthy and protenious bar",  null, "Food", null, 45.2);
    assertThrows(RuntimeException.class, () -> {
      productService.createProduct(product);
    });
  }

  @Test
  @DisplayName("List all products")
  public void testListProducts() throws Exception {
    Product product1 = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    productService.createProduct(product1);
    Product product2 = new Product("A domestic soap", "Detergent", "Domestic", null, 50.1);
    productService.createProduct(product2);
    Product product3 = new Product("An agriculture bar", "Compost", "Agriculture", null, 35.2);
    productService.createProduct(product3);
    List<Product> productList = productService.listAllProducts();

    assertEquals(productList.size(), 3);

  }

  @Test
  @DisplayName("Throws an error if no products found")
  public void testListProductsWithError() throws Exception {
    assertThrows(NoDataFoundException.class, () -> {
      productService.listAllProducts();
    });
  }

  @Test
  @DisplayName("Get product details by Id")
  public void testGetProduct() throws Exception {
    Product product = new Product("An agriculture bar", "Compost", "Agriculture", null, 35.2);
    Product savedProduct = productService.createProduct(product);
    Product response = productService.getProductDetails(savedProduct.getId());
    assertNotNull(response.getId());
    assertEquals(response.getDetails(), product.getDetails());

  }

  @Test
  @DisplayName("Throws an error if product is not found")
  public void testGetProductWithError() throws Exception {
    assertThrows(ProductNotFoundException.class, () -> {
      productService.getProductDetails(UUID.randomUUID());
    });
  }



}
