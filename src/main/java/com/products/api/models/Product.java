package com.products.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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

  public Product() {
  }

  public Product(String details, String title, String category) {
    this.details = details;
    this.title = title;
    this.category = category;
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
}
