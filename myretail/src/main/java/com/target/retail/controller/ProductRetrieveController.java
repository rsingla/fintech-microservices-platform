package com.target.retail.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.service.ProductRetrieveService;

@Controller
@RequestMapping("/v1")
public class ProductRetrieveController extends ControllerExceptionHandler {

  @Autowired
  ProductRetrieveService productRetrieveService;

  /**
   * This API is for retrieving Product data by Id
   * @throws ApiException 
   */
  @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  @CrossOrigin
  public ResponseEntity<Product> retrieveProductById(@PathVariable("id") String productId) throws URISyntaxException, ApiException {

    Product product = productRetrieveService.retrieveProduct(productId);

    return new ResponseEntity<>(product, HttpStatus.OK);
  }

}
