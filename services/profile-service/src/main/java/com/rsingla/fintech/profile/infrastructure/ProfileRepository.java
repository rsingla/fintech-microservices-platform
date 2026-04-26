package com.rsingla.fintech.profile.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    @Query("select new com.rsingla.fintech.profile.infrastructure.ProfileSummary(p.id) from ProfileEntity p where lower(p.email) = lower(:email)")
    Optional<ProfileSummary> findByEmailIgnoreCase(String email);
}
