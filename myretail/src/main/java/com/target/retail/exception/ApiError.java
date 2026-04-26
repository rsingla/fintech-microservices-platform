package com.target.retail.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonPropertyOrder({
  "field", "message", "code" })
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

  @JsonProperty("message")
  private String message;
  @JsonProperty("code")
  private String code;
  @JsonProperty("field")
  private String field;

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the field
   */
  public String getField() {
    return field;
  }

  /**
   * @param field the field to set
   */
  public void setField(String field) {
    this.field = field;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + ((field == null) ? 0 : field.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null) return false;
    if(getClass() != obj.getClass()) return false;
    ApiError other = (ApiError) obj;
    if(code == null) {
      if(other.code != null) return false;
    }
    else if(!message.equals(other.message)) return false;
    if(field == null) {
      if(other.field != null) return false;
    }
    else if(!field.equals(other.field)) return false;
    return true;

  }

}
