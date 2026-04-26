package com.rsingla.fintech.profile.api;

import com.rsingla.fintech.profile.domain.Address;
import com.rsingla.fintech.profile.domain.CustomerProfile;
import java.time.Instant;
import java.time.LocalDate;

public record ProfileResponse(
    String id,
    String firstName,
    String lastName,
    LocalDate dateOfBirth,
    String email,
    String phoneNumber,
    Address address,
    Instant createdAt,
    Instant updatedAt
) {
    static ProfileResponse from(CustomerProfile profile) {
        return new ProfileResponse(profile.id(), profile.firstName(), profile.lastName(), profile.dateOfBirth(), profile.email(), profile.phoneNumber(), profile.address(), profile.createdAt(), profile.updatedAt());
    }
}
