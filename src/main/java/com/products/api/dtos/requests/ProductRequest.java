package com.products.api.dtos.requests;


import javax.persistence.Entity;

public class ProductRequest {
  private String details;
  private String title;
  private String category;

  public ProductRequest(String details, String title, String category) {
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
