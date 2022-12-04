package com.products.api.repositories;

import com.products.api.ProductApiApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ProductApiApplication.class)
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

}

