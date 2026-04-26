package com.target.retail.exception;

import java.util.Set;

public class ApiException extends Exception {

  private static final long serialVersionUID = 1L;
  private String message;
  private String code;
  private String field;
  private int priority;
  private ApiError apiError = null;
  private Set<ApiError> apiErrors = null;

  public ApiException(String message, String code) {
    this.message = message;
    this.code = code;
  }

  public ApiException(String message, String code, String field) {
    this.message = message;
    this.code = code;
    this.field = field;
  }

  public ApiException(String message, String code, String field, int priority) {
    this.message = message;
    this.code = code;
    this.field = field;
    this.priority = priority;
  }

  public ApiException(ApiError apiError) {
    this.apiError = apiError;
  }

  public ApiException(Set<ApiError> apiErrors) {
    this.apiErrors = apiErrors;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public ApiError getApiError() {
    return apiError;
  }

  public void setApiError(ApiError apiError) {
    this.apiError = apiError;
  }

  public Set<ApiError> getApiErrors() {
    return apiErrors;
  }

  public void setApiErrors(Set<ApiError> apiErrors) {
    this.apiErrors = apiErrors;
  }

}
