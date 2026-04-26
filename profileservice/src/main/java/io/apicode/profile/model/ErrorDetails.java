package io.apicode.profile.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "errortime", "subject", "messages" })
public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 4106049665684682584L;
	@JsonProperty("errortime")
	Date errortime;
	@JsonProperty("subject")
	String subject;
	@JsonProperty("messages")
	List<String> messages;

	public ErrorDetails(Date errortime, String subject, List<String> errorMessages) {
		super();
		this.errortime = errortime;
		this.subject = subject;
		this.messages = errorMessages;
	}

	public Date getErrortime() {
		return errortime;
	}

	public void setErrortime(Date errortime) {
		this.errortime = errortime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
