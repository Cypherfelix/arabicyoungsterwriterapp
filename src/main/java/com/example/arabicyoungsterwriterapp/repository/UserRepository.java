package com.example.arabicyoungsterwriterapp.repository;

import com.example.arabicyoungsterwriterapp.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    public UserRepository() {
    }

    public boolean addUser(User user) {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collection = db.collection("users").document(user.getEmail()).set(user);

        try {
            collection.get();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public User getUser(String name, String password) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = ref.get();
        try {
            DocumentSnapshot snapshot = future.get();
            User user;
            if (snapshot.exists()) {
                user = snapshot.toObject(User.class);
                assert user != null;
                if (user.getPassword().equals(password)) {
                    return user;
                }
                return null;
            }
            return null;

        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

    }

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        List<User> users = new ArrayList<User>();
        // Get a reference to the Firestore database
        Firestore db = FirestoreClient.getFirestore();

        // Define a reference to the users collection
        CollectionReference usersRef = db.collection("users");

        // Retrieve all documents from the users collection

        ApiFuture<QuerySnapshot> querySnapshot = usersRef.get();

        for (QueryDocumentSnapshot docSnapshot : querySnapshot.get()){
            users.add(docSnapshot.toObject(User.class));
        }
        return users;
    }

}
