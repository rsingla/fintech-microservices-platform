package com.rsingla.fintech.profile.domain;

import java.time.Instant;
import java.time.LocalDate;

public record CustomerProfile(
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
}
