package com.rsingla.fintech.profile.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ProfileRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    LocalDate dateOfBirth,
    @Email @NotBlank String email,
    String phoneNumber,
    @Valid AddressRequest address
) {
    public record AddressRequest(
        @NotBlank String line1,
        String line2,
        @NotBlank String city,
        @NotBlank String state,
        @Size(min = 5, max = 20) String postalCode,
        @NotBlank String country
    ) {
    }
}
