package com.products.api.repositories;

import com.products.api.ProductApiApplication;
import com.products.api.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ProductApiApplication.class)
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @BeforeEach
  public void setup() {
    productRepository.deleteAll();
  }


  @Test
  public void testFindById() {
    Product product = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    productRepository.save(product);
    Optional<Product> result = productRepository.findById(product.getId());
    assertEquals(result.get().getId(), product.getId());
  }

  @Test
  public void testFindAll() {
    Product product1 = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    Product product2 = new Product("A domestic soap", "Detergent", "Domestic", null, 50.1);
    Product product3 = new Product("An agriculture bar", "Compost", "Agriculture", null, 35.2);

    List<Product> productList = Arrays.asList(product1, product2, product3);
    productRepository.saveAll(productList);
    List<Product> result = productRepository.findAll();
    assertEquals(result.size(), 3);
  }

}

