package io.apicode.profile.model;

import com.fasterxml.jackson.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Id", "first_name", "last_name", "date_of_birth", "phone_number", "timezone", "email", "address" })
public class Profile implements Serializable {

	private static final long serialVersionUID = -167823424587789469L;

	@JsonProperty("Id")
	private String id;

	@JsonProperty("first_name")
	@NotNull(message = "First Name cannot be null")
	private String firstName;

	@JsonProperty("last_name")
	@NotNull(message = "Last Name cannot be null")
	private String lastName;

	@JsonProperty("date_of_birth")
	private LocalDate dateOfBirth;

	@JsonProperty("phone_number")
	private String phoneNumber;

	@JsonProperty("timezone")
	private String timezone;

	@JsonProperty("email")
	@NotNull(message = "Email cannot be null")
	@Email(message="Email Format is incorrect")
	private String email;

	@JsonProperty("address")
	@Valid
	private Address address;

	@JsonProperty("time_updated")
	private Instant timeUpdated;

	@JsonIgnore
	@Valid
	private Map<String, Object> additionalProperties = new HashMap<>();

	public Profile() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Instant getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Instant timeUpdated) {
		this.timeUpdated = timeUpdated;
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
		return "Profile{" +
				"id='" + id + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", phoneNumber='" + phoneNumber + '\'' +
				", timezone='" + timezone + '\'' +
				", email='" + email + '\'' +
				", address=" + address +
				", timeUpdated=" + timeUpdated +
				", additionalProperties=" + additionalProperties +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Profile profile = (Profile) o;
		return Objects.equals(id, profile.id) &&
				Objects.equals(firstName, profile.firstName) &&
				Objects.equals(lastName, profile.lastName) &&
				Objects.equals(dateOfBirth, profile.dateOfBirth) &&
				Objects.equals(phoneNumber, profile.phoneNumber) &&
				Objects.equals(timezone, profile.timezone) &&
				Objects.equals(email, profile.email) &&
				Objects.equals(address, profile.address) &&
				Objects.equals(timeUpdated, profile.timeUpdated) &&
				Objects.equals(additionalProperties, profile.additionalProperties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, dateOfBirth, phoneNumber, timezone, email, address, timeUpdated, additionalProperties);
	}
}