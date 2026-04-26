package com.target.api.test.util;

public enum EnvironmentUrl {

  LOCAL("http://localhost:8080");

  private String envUrl;

  EnvironmentUrl(String environment) {
    envUrl = environment;
  }

  public String getEnvUrl() {
    return envUrl;
  }

  public void setEnvUrl(String envUrl) {
    this.envUrl = envUrl;
  }

}
