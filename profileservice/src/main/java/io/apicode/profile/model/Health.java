package io.apicode.profile.model;

import org.springframework.boot.actuate.health.HealthComponent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "readiness", "liveness", "service_detail" })
public class Health {

	@JsonProperty("readiness")
	HealthComponent readiness;

	@JsonProperty("liveness")
	HealthComponent liveness;

	@JsonProperty("service_detail")
	String servicedetails;

	public HealthComponent getReadiness() {
		return readiness;
	}

	public void setReadiness(HealthComponent readiness) {
		this.readiness = readiness;
	}

	public HealthComponent getLiveness() {
		return liveness;
	}

	public void setLiveness(HealthComponent liveness) {
		this.liveness = liveness;
	}

	public String getServicedetails() {
		return servicedetails;
	}

	public void setServicedetails(String servicedetails) {
		this.servicedetails = servicedetails;
	}

}
