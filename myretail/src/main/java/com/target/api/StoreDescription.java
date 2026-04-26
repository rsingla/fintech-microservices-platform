
package com.target.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "value", "type" })
public class StoreDescription {

  @JsonProperty("value")
  private String value;
  @JsonProperty("type")
  private String type;

  /**
   * @return The value
   */
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  /**
   * @param value The value
   */
  @JsonProperty("value")
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return The type
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * @param type The type
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

}
