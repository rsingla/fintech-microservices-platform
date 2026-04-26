package com.target.retail.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id", "product_id", "name", "current_price" })
@Document(collection = "product")
public class Product {

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("product_id")
  @NotBlank(message = "Product Id cannot be null", groups = ProductValidate.class)
  private String productId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("current_price")
  @Valid
  private CurrentPrice currentPrice;

  /**
   * @return The id
   */
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  /**
   * @param id The id
   */
  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return The productId
   */
  @JsonProperty("product_id")
  public String getProductId() {
    return productId;
  }

  /**
   * @param productId The product_id
   */
  @JsonProperty("product_id")
  public void setProductId(String productId) {
    this.productId = productId;
  }

  /**
   * @return The name
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * @param name The name
   */
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return The currentPrice
   */
  @JsonProperty("current_price")
  public CurrentPrice getCurrentPrice() {
    return currentPrice;
  }

  /**
   * @param currentPrice The current_price
   */
  @JsonProperty("current_price")
  public void setCurrentPrice(CurrentPrice currentPrice) {
    this.currentPrice = currentPrice;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
