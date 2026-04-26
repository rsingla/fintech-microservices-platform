package com.target.api.call.integration;

import java.io.IOException;

import io.restassured.RestAssured;
import com.target.api.call.JsonParserUtil;
import com.target.api.test.util.EnvironmentUrl;
import com.target.api.test.util.FileReader;
import com.target.retail.model.Product;

public class RestAssuredUtil {

  public static Product productAdd(String jsonName) throws IOException {

    Product product = JsonParserUtil.serializeStr2JsonModel(Product.class, FileReader.readFile(jsonName));

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/";

    Product productAdded = RestAssured.given().accept("application/json").contentType("application/json").when()
        .body(product).post(url).as(Product.class);

    return productAdded;
  }

  public static Product productRetrieve(String id) throws IOException {

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/" + id;

    Product productRetrieve = RestAssured.given().accept("application/json").contentType("application/json").when()
        .get(url).as(Product.class);

    return productRetrieve;
  }

  public static Boolean productDelete(String id) throws IOException {

    String url = EnvironmentUrl.LOCAL.getEnvUrl() + "/myretail/v1/product/" + id;

    Boolean response = RestAssured.given().accept("application/json").contentType("application/json").when().delete(url)
        .as(Boolean.class);
    
    return response;
  }
}
