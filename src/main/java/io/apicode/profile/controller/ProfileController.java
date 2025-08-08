package io.apicode.profile.controller;

import io.apicode.profile.exception.ProfileNotFoundException;
import io.apicode.profile.model.Profile;
import io.apicode.profile.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

	private final Logger log = Logger.getLogger("Profile Controller");

	private final UserService userService;

	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<Profile> getProfileDetails(@PathVariable String id) {
		Profile profile = userService.getProfileDetails(id)
				.orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + id));
		return ResponseEntity.ok(profile);
	}

	@PostMapping(produces = { "application/json" })
	public Profile createProfile(@Valid @RequestBody Profile profile) {
		return userService.createNewProfile(profile);
	}

	@PutMapping(path = "/{id}", produces = { "application/json" })
	public Profile updateProfile(@Valid @RequestBody Profile profile, @PathVariable String id) {
		return userService.updateExistingProfile(id, profile);
	}

	@PatchMapping(path = "/{id}", produces = { "application/json" })
	public Profile upsertProfile(@RequestBody Profile profile, @PathVariable String id) {
		return userService.patchUpdateProfile(id, profile);
	}

	@DeleteMapping(path = "/{id}", produces = { "application/json" })
	public String deleteProfileById(@PathVariable String id) {
		return userService.deleteProfile(id);
	}

}
