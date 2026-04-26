package com.target.retail.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.retail.exception.ApiException;
import com.target.retail.model.Product;
import com.target.retail.service.ProductUpdateService;

@Controller
@RequestMapping("/v1")
public class ProductPriceUpdateController extends ControllerExceptionHandler {

  @Autowired
  ProductUpdateService productUpdateService;

  /**
   * This API is for retrieving Product data by Id
   * @throws ApiException
   */
  @RequestMapping(value = "/product/", method = RequestMethod.PUT, produces = "application/json")
  @ResponseBody
  @CrossOrigin
  public ResponseEntity<Product> updateProductPrice(@RequestBody Product product)
      throws URISyntaxException, ApiException {

    Product productDB = productUpdateService.updateProductPrice(product);

    return new ResponseEntity<>(productDB, HttpStatus.OK);
  }

}
