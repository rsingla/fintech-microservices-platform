package com.target.api.call;

import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.target.api.Item;
import com.target.api.Products;

@Component
public class RetrieveProductName {

  @Autowired
  RestTemplate restTemplate;

  public static final Logger log = LoggerFactory.getLogger("myretail");

  public static String BASEURL = "https://api.target.com/products/v3/";
  public static String PARAM1 = "fields";
  public static String PARAM2 = "id_type";
  public static String PARAM3 = "key";
  public static String PARAM1VALUE = "descriptions";

  @Value("${api.target.key.value}")
  private String param3KeyValue;

  @Value("${api.target.id.type}")
  private String param2IdTypeValue;

  public Products getProductsDetail(String id) throws URISyntaxException {

    URIBuilder url = new URIBuilder(BASEURL + id);
    url.setParameter(PARAM1, PARAM1VALUE);
    url.setParameter(PARAM2, param2IdTypeValue);
    url.setParameter(PARAM3, param3KeyValue);

    log.info("Calling URL: " + url.build());
    Products products = null;
    try {
      String value = restTemplate.getForEntity(url.build(), String.class).getBody();
      products = JsonParserUtil.serializeStr2JsonModel(Products.class, value);
    }
    catch(RestClientException | URISyntaxException e) {
      e.printStackTrace();
    }
    return products;
  }

  public String retrieveProductName(String id) throws URISyntaxException {

    String name = null;
    Products products = getProductsDetail(id);

    if(checkIfErrorExist(products)) {
      return name;
    }

    if(products != null && products.getProductCompositeResponse() != null
        && products.getProductCompositeResponse().getItems() != null) {
      Optional<Item> itemData = products.getProductCompositeResponse().getItems().parallelStream().findFirst();
      if(itemData.isPresent()) {
        name = descriptionValue(itemData.get());
      }
    }
    return name;
  }

  private String descriptionValue(Item item) {
    String name = item.getGeneralDescription();
    if(name == null || name.length() < 1) {
      name = item.getOnlineDescription().getValue();
    }
    if(name == null || name.length() < 1) {
      name = item.getAlternateDescription().get(0).getTypeDescription();
    }
    return name;
  }

  private boolean checkIfErrorExist(Products products) {
    if(products != null && products.getProductCompositeResponse() != null
        && products.getProductCompositeResponse().getItems() != null) {
      Optional<Item> itemData = products.getProductCompositeResponse().getItems().parallelStream().findFirst();

      // Checking if itemData is Present
      if(itemData.isPresent()) {
        Item item = itemData.get();
        if(item.getErrors() != null && !item.getErrors().isEmpty()) {
          Error error = item.getErrors().parallelStream().findFirst().get();
          if(error.getMessage() != null) {
            return true;
          }
        }
      }
    }
    return false;
  }

}
