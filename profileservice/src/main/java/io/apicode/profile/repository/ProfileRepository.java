package io.apicode.profile.repository;

import io.apicode.profile.model.Profile;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface ProfileRepository {

    Profile saveProfile(Profile profile) throws InterruptedException, ExecutionException;

    Optional<Profile> findProfileById(String id) throws InterruptedException, ExecutionException;

    Profile updateProfile(String id, Profile profile) throws InterruptedException, ExecutionException;

    Optional<String> findProfileByEmail(String email) throws InterruptedException, ExecutionException;

    void deleteProfileById(String id) throws InterruptedException, ExecutionException;

}
