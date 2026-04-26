package io.apicode.profile;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.FileInputStream;

@Configuration
public class FirestoreConfiguration {

	@Value("${firebase.credential.path}")
	public String credentialPath;

	@Value("${firebase.db.url}")
	String fireBaseDBUrl;

	@Autowired
	ResourceLoader resourceLoader;

	public Resource loadDBCredentialFile() {
		return resourceLoader.getResource(credentialPath);
	}

	@jakarta.annotation.PostConstruct
	public void initialize() {
		try {
			Resource resource = loadDBCredentialFile();
			FileInputStream serviceAccount = new FileInputStream(resource.getFile());

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl(fireBaseDBUrl).build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Firestore firestore() {
		return FirestoreClient.getFirestore();
	}
}