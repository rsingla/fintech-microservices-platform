package com.target.api.call.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.target.retail.model.Product;

public class ProductInsertAndRetrieveAll {

  @Test
  public void insertProduct1Data() throws IOException {

    Product product = RestAssuredUtil.productAdd("request1.json");

    assertNotNull(product.getId());

    assertNotNull(RestAssuredUtil.productRetrieve(product.getProductId()));

    assertTrue(RestAssuredUtil.productDelete(product.getProductId()));
  }

  @Test
  public void insertProduct3Data() throws IOException {

    Product product = RestAssuredUtil.productAdd("request3.json");

    assertNotNull(product.getId());

    assertNotNull(RestAssuredUtil.productRetrieve(product.getProductId()));

    assertTrue(RestAssuredUtil.productDelete(product.getProductId()));
  }

  @Test
  public void insertProduct4Data() throws IOException {

    Product product = RestAssuredUtil.productAdd("request4.json");

    assertNotNull(product.getId());

    assertNotNull(RestAssuredUtil.productRetrieve(product.getProductId()));

    assertTrue(RestAssuredUtil.productDelete(product.getProductId()));

  }

  @Test
  public void insertProduct5Data() throws IOException {

    Product product = RestAssuredUtil.productAdd("request5.json");

    assertNotNull(product.getId());

    assertNotNull(RestAssuredUtil.productRetrieve(product.getProductId()));

    assertTrue(RestAssuredUtil.productDelete(product.getProductId()));
  }

  @Test
  public void insertProduct6Data() throws IOException {

    Product product = RestAssuredUtil.productAdd("request6.json");

    assertNotNull(product.getId());

    assertNotNull(RestAssuredUtil.productRetrieve(product.getProductId()));

    assertTrue(RestAssuredUtil.productDelete(product.getProductId()));
  }

}
