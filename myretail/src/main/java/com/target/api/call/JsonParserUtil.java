package com.target.api.call;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonParserUtil {

  public static <T> String searlizeJson2Str(Class<T> clazz, T pojo) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String json = null;
    try {
      json = mapper.writeValueAsString(pojo);
    }
    catch(JsonProcessingException e) {
      e.printStackTrace();
    }
    return json;
  }

  public static <T> T serializeStr2JsonModel(Class<T> clazz, String json) {
    if(json == null) {
      return null;
    }

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    T pojo = null;
    try {
      pojo = mapper.readValue(new String(json), clazz);
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    return pojo;
  }

}
