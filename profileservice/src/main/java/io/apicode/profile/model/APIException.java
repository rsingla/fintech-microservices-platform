package io.apicode.profile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "message"})	
public class APIException extends Exception {

	private static final long serialVersionUID = 7705275064137301494L;

	@JsonProperty("message")
	String message;

	public APIException(String message) {
		this.message = message;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

}
