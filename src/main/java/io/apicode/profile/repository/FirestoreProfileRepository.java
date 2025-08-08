package io.apicode.profile.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import io.apicode.profile.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public class FirestoreProfileRepository implements ProfileRepository {

    private final Firestore firestore;
    private static final String COL_NAME = "profiles";

    public FirestoreProfileRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Profile saveProfile(Profile profile) throws InterruptedException, ExecutionException {
        String id = getID();
        profile.setId(id);
        ApiFuture<WriteResult> collectionsApiFuture = getCollection().document(id).set(profile);
        profile.setTimeUpdated(collectionsApiFuture.get().getUpdateTime().toDate().toInstant());
        return profile;
    }

    @Override
    public Optional<Profile> findProfileById(String id) throws InterruptedException, ExecutionException {
        DocumentReference documentReference = getCollection().document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return Optional.ofNullable(document.toObject(Profile.class));
        }
        return Optional.empty();
    }

    @Override
    public Profile updateProfile(String id, Profile profile) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> collectionsApiFuture = getCollection().document(id).set(profile);
        profile.setTimeUpdated(collectionsApiFuture.get().getUpdateTime().toDate().toInstant());
        return profile;
    }

    @Override
    public Optional<String> findProfileByEmail(String email) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = getCollection().whereEqualTo("email", email).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (!documents.isEmpty()) {
            // Assuming email is unique, return the first one found.
            return Optional.of(documents.get(0).getId());
        }
        return Optional.empty();
    }

    @Override
    public void deleteProfileById(String id) throws InterruptedException, ExecutionException {
        getCollection().document(id).delete().get(); // .get() to wait for completion
    }

    private String getID() {
        return UUID.randomUUID().toString();
    }

    private CollectionReference getCollection() {
        return firestore.collection(COL_NAME);
    }
}
