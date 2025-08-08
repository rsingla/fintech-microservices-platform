package io.apicode.profile.service;

import io.apicode.profile.exception.DataAccessException;
import io.apicode.profile.exception.DuplicateProfileException;
import io.apicode.profile.exception.ProfileNotFoundException;
import io.apicode.profile.model.Profile;
import io.apicode.profile.repository.ProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final ProfileRepository profileRepository;

    public UserService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createNewProfile(Profile profile) {
        getByEmail(profile.getEmail()).ifPresent(id -> {
            throw new DuplicateProfileException("Profile with email " + profile.getEmail() + " already exists.");
        });
        return saveProfileDetails(profile);
    }

    public Profile updateExistingProfile(String id, Profile profile) {
        Profile existingProfile = getProfileDetails(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + id));

        getByEmail(profile.getEmail()).ifPresent(existingId -> {
            if (!existingId.equals(id)) {
                throw new DuplicateProfileException("Email " + profile.getEmail() + " is already in use by another profile.");
            }
        });

        return updateProfileDetails(profile, id);
    }

    public Profile patchUpdateProfile(String id, Profile profile) {
        Profile existingProfile = getProfileDetails(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + id));

        if (profile.getEmail() != null) {
            getByEmail(profile.getEmail()).ifPresent(existingId -> {
                if (!existingId.equals(id)) {
                    throw new DuplicateProfileException("Email " + profile.getEmail() + " is already in use by another profile.");
                }
            });
        }

        BeanUtils.copyProperties(profile, existingProfile, getNullPropertyNames(profile));
        return updateProfileDetails(existingProfile, id);
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    public Profile saveProfileDetails(Profile profile) {
        try {
            return profileRepository.saveProfile(profile);
        } catch (InterruptedException | ExecutionException e) {
            throw new DataAccessException("Error while saving profile", e);
        }
    }

    public Optional<Profile> getProfileDetails(String id) {
        try {
            return profileRepository.findProfileById(id);
        } catch (InterruptedException | ExecutionException e) {
            throw new DataAccessException("Error while fetching profile by id: " + id, e);
        }
    }

    public Profile updateProfileDetails(Profile profile, String id) {
        try {
            return profileRepository.updateProfile(id, profile);
        } catch (InterruptedException | ExecutionException e) {
            throw new DataAccessException("Error while updating profile: " + id, e);
        }
    }

    public Optional<String> getByEmail(String email) {
        try {
            return profileRepository.findProfileByEmail(email);
        } catch (InterruptedException | ExecutionException e) {
            throw new DataAccessException("Error while fetching profile by email: " + email, e);
        }
    }

    public String deleteProfile(String id) {
        getProfileDetails(id).orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + id));
        try {
            profileRepository.deleteProfileById(id);
            return "Document with Profile ID " + id + " has been deleted";
        } catch (InterruptedException | ExecutionException e) {
            throw new DataAccessException("Error while deleting profile: " + id, e);
        }
    }
}