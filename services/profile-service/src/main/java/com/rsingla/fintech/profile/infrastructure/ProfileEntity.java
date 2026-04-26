package com.rsingla.fintech.profile.infrastructure;

import com.rsingla.fintech.profile.api.ProfileRequest;
import com.rsingla.fintech.profile.application.ProfileService;
import com.rsingla.fintech.profile.domain.Address;
import com.rsingla.fintech.profile.domain.CustomerProfile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "customer_profiles")
public class ProfileEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Instant createdAt;
    private Instant updatedAt;

    protected ProfileEntity() {
    }

    public static ProfileEntity from(String id, ProfileRequest request, Instant createdAt, Instant updatedAt) {
        var entity = new ProfileEntity();
        entity.id = id;
        entity.createdAt = createdAt;
        entity.apply(request, updatedAt);
        return entity;
    }

    public void apply(ProfileRequest request, Instant updatedAt) {
        this.firstName = request.firstName();
        this.lastName = request.lastName();
        this.dateOfBirth = request.dateOfBirth();
        this.email = request.email();
        this.phoneNumber = request.phoneNumber();
        this.updatedAt = updatedAt;
        var address = ProfileService.toAddress(request.address());
        if (address != null) {
            this.addressLine1 = address.line1();
            this.addressLine2 = address.line2();
            this.city = address.city();
            this.state = address.state();
            this.postalCode = address.postalCode();
            this.country = address.country();
        }
    }

    public CustomerProfile toDomain() {
        var address = addressLine1 == null ? null : new Address(addressLine1, addressLine2, city, state, postalCode, country);
        return new CustomerProfile(id, firstName, lastName, dateOfBirth, email, phoneNumber, address, createdAt, updatedAt);
    }
}
