
package com.target.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id_type", "id", "is_primary", "source" })
public class Identifier {

  @JsonProperty("id_type")
  private String idType;
  @JsonProperty("id")
  private String id;
  @JsonProperty("is_primary")
  private Object isPrimary;
  @JsonProperty("source")
  private String source;

  /**
   * @return The idType
   */
  @JsonProperty("id_type")
  public String getIdType() {
    return idType;
  }

  /**
   * @param idType The id_type
   */
  @JsonProperty("id_type")
  public void setIdType(String idType) {
    this.idType = idType;
  }

  /**
   * @return The id
   */
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  /**
   * @param id The id
   */
  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return The isPrimary
   */
  @JsonProperty("is_primary")
  public Object getIsPrimary() {
    return isPrimary;
  }

  /**
   * @param isPrimary The is_primary
   */
  @JsonProperty("is_primary")
  public void setIsPrimary(Object isPrimary) {
    this.isPrimary = isPrimary;
  }

  /**
   * @return The source
   */
  @JsonProperty("source")
  public String getSource() {
    return source;
  }

  /**
   * @param source The source
   */
  @JsonProperty("source")
  public void setSource(String source) {
    this.source = source;
  }

}
