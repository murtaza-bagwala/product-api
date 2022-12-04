package com.products.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.models.Product;
import com.products.api.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

  @MockBean
  private ProductRepository productRepository;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    productRepository.deleteAll();
  }

  @Test
  @DisplayName("Creates a Product with title, details, price, size and category")
  public void testCreateProduct() throws Exception {
    Product product = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.50);
    Mockito.when(productRepository.save(product)).thenReturn(product);
    mockMvc.perform( MockMvcRequestBuilders
            .post("/api/products")
            .content(asJsonString(product))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.details").value("A healthy and protenious bar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Protein Bar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(45.50));
  }

  @Test
  @DisplayName("Returns client error if title, description or price are not passed")
  public void testCreateProductWithMissingDate() throws Exception {
    Product product = new Product("A healthy and protenious bar", null, "Food", null, 45.2);
    mockMvc.perform( MockMvcRequestBuilders
            .post("/api/products")
            .content(asJsonString(product))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists());

  }

  @Test
  @DisplayName("Returns a list of Product with uuid, title, details, price, size and category")
  public void testGetProducts() throws Exception {
    Product product1 = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    Product product2 = new Product("A domestic soap", "Detergent", "Domestic", null, 50.1);
    Product product3 = new Product("An agriculture bar", "Compost", "Agriculture", null, 35.2);

    List<Product> productList = Arrays.asList(product1, product2, product3);
    Mockito.when(productRepository.findAll()).thenReturn(productList);
    mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(45.2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price").value(50.1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[2].price").value(35.2));

  }

  @Test
  @DisplayName("Returns no data found if product list is empty")
  public void testGetProductsWithMissingProducts() throws Exception {
    mockMvc.perform( MockMvcRequestBuilders
            .get("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No Product found"));
  }

  @Test
  @DisplayName("Returns product details if id is passed")
  public void testGetProduct() throws Exception {
    Product product = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    product.setId(UUID.randomUUID());
    Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
    mockMvc.perform( MockMvcRequestBuilders
                    .get("/api/products/"+product.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details").value("A healthy and protenious bar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Protein Bar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(45.2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId().toString()));
  }

  @Test
  @DisplayName("Returns not found if product details not found")
  public void testGetProductWithMissingProduct() throws Exception {
    Product product = new Product("A healthy and protenious bar", "Protein Bar", "Food", null, 45.2);
    product.setId(UUID.randomUUID());
    Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(null));
    mockMvc.perform( MockMvcRequestBuilders
                    .get("/api/products/"+product.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Product not found"));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
