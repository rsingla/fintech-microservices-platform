package com.target.api.call.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.target.api.call.JsonParserUtil;
import com.target.api.test.util.EnvironmentUrl;
import com.target.api.test.util.FileReader;
import com.target.retail.model.Product;

public class ProductInsert {

  @Test
  public void insertApplicationData() throws IOException {

    Product product = JsonParserUtil.serializeStr2JsonModel(Product.class, FileReader.readFile("request8.json"));

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/";

    Product productAdded = RestAssured.given().accept("application/json").contentType("application/json").when()
        .body(product).post(url).as(Product.class);

    System.out.println(productAdded);

    assertNotNull(productAdded.getId());

    assertTrue(RestAssuredUtil.productDelete(productAdded.getProductId()));

  }

  @Test
  public void insertApplicationWhenApplicationIsMissing() throws IOException {

    Product product = JsonParserUtil.serializeStr2JsonModel(Product.class, FileReader.readFile("request2.json"));

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/";

    Response restAssuredResponse = RestAssured.given().accept("application/json").contentType("application/json").when()
        .body(product).post(url).andReturn();

    String value = restAssuredResponse.asString();

    assertNotNull(value);
    assertTrue(value.contains("RETAIL-103"));

  }

}
