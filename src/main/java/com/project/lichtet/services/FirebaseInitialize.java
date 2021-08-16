package com.project.lichtet.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = this.getClass().getClassLoader()
                    .getResourceAsStream("./lichgodep-9cd73-firebase-adminsdk-3cn0q-ec262eefd1.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }
}
