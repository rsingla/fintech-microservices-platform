package com.rsingla.fintech.profile.api;

import com.rsingla.fintech.common.PageResponse;
import com.rsingla.fintech.profile.application.ProfileService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
class ProfileController {
    private final ProfileService profiles;

    ProfileController(ProfileService profiles) {
        this.profiles = profiles;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProfileResponse create(@Valid @RequestBody ProfileRequest request) {
        return ProfileResponse.from(profiles.create(request));
    }

    @GetMapping("/{id}")
    ProfileResponse get(@PathVariable String id) {
        return ProfileResponse.from(profiles.get(id));
    }

    @GetMapping
    PageResponse<ProfileResponse> list(Pageable pageable) {
        var page = profiles.list(pageable).map(ProfileResponse::from);
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @PutMapping("/{id}")
    ProfileResponse update(@PathVariable String id, @Valid @RequestBody ProfileRequest request) {
        return ProfileResponse.from(profiles.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        profiles.delete(id);
    }
}
