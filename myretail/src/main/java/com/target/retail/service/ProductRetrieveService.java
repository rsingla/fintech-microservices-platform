package com.target.retail.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.api.call.RetrieveProductName;
import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.product.service.ProductRepository;

@Component
public class ProductRetrieveService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  RetrieveProductName retrieveProductName;

  public Product retrieveProduct(String productId) throws URISyntaxException, ApiException {

    // Call ProductName
    String productName = retrieveProductName.retrieveProductName(productId);

    if(productName == null) {
      throw new ApiException("Not valid product in system: This product ID does not represent a valid product", "RETAIL-103");
    }

    Product product = productRepository.findByProductId(productId);
    if(product == null) {
      throw new ApiException("Product is not available in the database", "RETAIL-102");
    }
    // Set productName
    product.setName(productName);

    return product;
  }

}
