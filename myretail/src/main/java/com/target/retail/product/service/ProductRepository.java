package com.target.retail.product.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.target.retail.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

  @Query("{ 'name' : ?0 }")
  Product findByName(String name);

  @Query("{ 'productId' : ?0 }")
  Product findByProductId(String productId);

  void deleteByProductId(String productId);

}