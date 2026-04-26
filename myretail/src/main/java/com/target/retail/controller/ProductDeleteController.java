package com.target.retail.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.retail.exception.ApiException;
import com.target.retail.service.ProductDeleteService;

@Controller
@RequestMapping("/v1")
@Profile("test")
public class ProductDeleteController extends ControllerExceptionHandler {

  @Autowired
  ProductDeleteService productDeleteService;

  /**
   * This API is for retrieving Product data by Id
   * @throws ApiException
   */
  @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, produces = "application/json")
  @ResponseBody
  @CrossOrigin
  public ResponseEntity<Boolean> deleteProduct(@PathVariable String id) throws URISyntaxException, ApiException {

    productDeleteService.deleteProduct(id);

    return new ResponseEntity<>(true, HttpStatus.OK);
  }

}
