package io.apicode.profile.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "street1", "street2", "city", "state", "zip_code" })
public class Address implements Serializable {

	@JsonProperty("street1")
	@NotNull(message = "Street1 cannot be null")
	private String street1;
	@JsonProperty("street2")
	private String street2;
	@JsonProperty("city")
	@NotNull(message = "City cannot be null")
	private String city;
	@JsonProperty("state")
	@NotNull(message = "State cannot be null")
	private String state;
	@JsonProperty("zip_code")
	@NotNull(message = "ZipCode cannot be null")
	@Size(min = 5, max = 20, message 
    = "Zip Code cannot be less than 5 character")
	private String zipCode;
	@JsonIgnore
	@Valid
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	private final static long serialVersionUID = -7093840734746299724L;

	public Address() {
	}

	@JsonProperty("street1")
	public String getStreet1() {
		return street1;
	}

	@JsonProperty("street1")
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	@JsonProperty("street2")
	public String getStreet2() {
		return street2;
	}

	@JsonProperty("street2")
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("zip_code")
	public String getZipCode() {
		return zipCode;
	}

	@JsonProperty("zip_code")
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Address.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("street1");
		sb.append('=');
		sb.append(((this.street1 == null) ? "<null>" : this.street1));
		sb.append(',');
		sb.append("street2");
		sb.append('=');
		sb.append(((this.street2 == null) ? "<null>" : this.street2));
		sb.append(',');
		sb.append("city");
		sb.append('=');
		sb.append(((this.city == null) ? "<null>" : this.city));
		sb.append(',');
		sb.append("state");
		sb.append('=');
		sb.append(((this.state == null) ? "<null>" : this.state));
		sb.append(',');
		sb.append("zipCode");
		sb.append('=');
		sb.append(((this.zipCode == null) ? "<null>" : this.zipCode));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
