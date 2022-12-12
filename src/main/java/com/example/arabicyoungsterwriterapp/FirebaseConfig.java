package com.example.arabicyoungsterwriterapp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private final Resource googleServicesJson = ArabicyoungsterwriterappApplication.listAllResources().get(0);

    public FirebaseConfig() throws IOException {
    }

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        System.out.println(googleServicesJson.getURL().toString());
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(googleServicesJson.getInputStream()))
                    .setDatabaseUrl("https://arabicstoryclient-567df-default-rtdb.firebaseio.com")
                    .build();

            if (FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
            }
            return FirebaseDatabase.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
