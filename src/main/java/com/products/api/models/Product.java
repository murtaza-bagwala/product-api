package com.products.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Product {
  @NotNull
  private String details;
  @NotNull
  private String title;
  @Id
  @GeneratedValue
  private UUID id;
  private String category;
  private String size;
  @NotNull
  @Min(1)
  private double price;

  public Product() {
  }

  public Product(String details, String title, String category) {
    this.details = details;
    this.title = title;
    this.category = category;
  }

  public Product(String details, String title, String category, String size, double price) {
    this.details = details;
    this.title = title;
    this.category = category;
    this.size = size;
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Double.compare(product.price, price) == 0 && Objects.equals(details, product.details) && Objects.equals(title, product.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(details, title, price);
  }
}
