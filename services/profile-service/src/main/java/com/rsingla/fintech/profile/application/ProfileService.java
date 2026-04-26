package com.rsingla.fintech.profile.application;

import com.rsingla.fintech.common.DomainException;
import com.rsingla.fintech.profile.api.ProfileRequest;
import com.rsingla.fintech.profile.domain.Address;
import com.rsingla.fintech.profile.domain.CustomerProfile;
import com.rsingla.fintech.profile.infrastructure.ProfileEntity;
import com.rsingla.fintech.profile.infrastructure.ProfileRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {
    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public CustomerProfile create(ProfileRequest request) {
        ensureEmailAvailable(request.email(), null);
        var now = Instant.now();
        return repository.save(ProfileEntity.from(UUID.randomUUID().toString(), request, now, now)).toDomain();
    }

    @Transactional(readOnly = true)
    public CustomerProfile get(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainException("Profile not found: " + id)).toDomain();
    }

    @Transactional(readOnly = true)
    public Page<CustomerProfile> list(Pageable pageable) {
        return repository.findAll(pageable).map(ProfileEntity::toDomain);
    }

    public CustomerProfile update(String id, ProfileRequest request) {
        ensureEmailAvailable(request.email(), id);
        var entity = repository.findById(id).orElseThrow(() -> new DomainException("Profile not found: " + id));
        entity.apply(request, Instant.now());
        return repository.save(entity).toDomain();
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DomainException("Profile not found: " + id);
        }
        repository.deleteById(id);
    }

    private void ensureEmailAvailable(String email, String currentProfileId) {
        repository.findByEmailIgnoreCase(email).ifPresent(existing -> {
            if (!existing.id().equals(currentProfileId)) {
                throw new DomainException("Email is already registered: " + email);
            }
        });
    }

    public static Address toAddress(ProfileRequest.AddressRequest request) {
        if (request == null) {
            return null;
        }
        return new Address(request.line1(), request.line2(), request.city(), request.state(), request.postalCode(), request.country());
    }
}
