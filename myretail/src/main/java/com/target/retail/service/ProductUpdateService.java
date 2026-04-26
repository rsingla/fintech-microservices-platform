package com.target.retail.service;

import java.net.URISyntaxException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.api.call.RetrieveProductName;
import com.target.api.call.ValidationUtil;
import com.target.retail.exception.ApiError;
import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.product.service.ProductRepository;

@Component
public class ProductUpdateService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductRetrieveService productRetrieveService;

  @Autowired
  RetrieveProductName retrieveProductName;

  @Autowired
  ValidationUtil validationUtil;

  public Product updateProductPrice(Product product) throws URISyntaxException, ApiException {

    Set<ApiError> apiErrors = validationUtil.dataValidation(product);

    if(!apiErrors.isEmpty()) {
      throw new ApiException(apiErrors);
    }

    String productId = product.getProductId();
    Product productValue = productRepository.findByProductId(productId);

    return updateDB(product, productValue);
  }

  public Product updateDB(Product product, Product productValue) {
    Product productDB = null;
    if(productValue == null) {
      productDB = productRepository.save(product);
    }
    else if(productValue != null) {
      productValue.setCurrentPrice(product.getCurrentPrice());
      productDB = productRepository.save(productValue);
    }
    return productDB;
  }

}
