package com.target.retail.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.target.api.call.RetrieveProductName;
import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.product.service.ProductRepository;

@Component
@Profile("test")
public class ProductDeleteService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductRetrieveService productRetrieveService;

  @Autowired
  RetrieveProductName retrieveProductName;

  /**
   * This method insert the data in the mongodb. Please visit {@link Product} for the request and response data.
   * @param product
   * @return
   * @throws ApiException
   * @throws URISyntaxException
   */
  public void deleteProduct(String id) {
    productRepository.deleteByProductId(id);
  }

}
