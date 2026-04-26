
package com.target.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "feature" })
public class Feature {

  @JsonProperty("feature")
  private String feature;

  /**
   * @return The feature
   */
  @JsonProperty("feature")
  public String getFeature() {
    return feature;
  }

  /**
   * @param feature The feature
   */
  @JsonProperty("feature")
  public void setFeature(String feature) {
    this.feature = feature;
  }

}
