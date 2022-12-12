package com.example.arabicyoungsterwriterapp.repository;

import com.example.arabicyoungsterwriterapp.model.Story;
import com.example.arabicyoungsterwriterapp.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
public class StoryRepositoryImpl implements StoryRepository {

    @Override
    public Boolean addStory(Story s) {
        if (exists(s)) {
            return false;
        }
        // Create a new Firestore client
        Firestore firestoreClient = FirestoreClient.getFirestore();

        // Get the collection
        ApiFuture<QuerySnapshot> future = firestoreClient.collection("stories").get();

        // Get the number of documents in the collection

        try {
            String numDocs = getNextId();
            s.setStoryID(numDocs);
            ApiFuture<WriteResult> collection = firestoreClient.collection("stories").document(numDocs).set(s);
            collection.get();
            return true;

        } catch (InterruptedException | ExecutionException e) {
            return false;
        }

    }

    @Override
    public Boolean updateStory(Story s) {
        // Create a new Firestore client
        Firestore firestoreClient = FirestoreClient.getFirestore();

        // Get a reference to the document you want to update
        DocumentReference docRef = firestoreClient.collection("stories").document(s.getStoryID());

        ApiFuture<WriteResult> collection = docRef.set(s);
        return true;
    }

    @Override
    public Story getStory(String storyID) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("stories").document(storyID);

        ApiFuture<DocumentSnapshot> future = ref.get();
        try {
            DocumentSnapshot snapshot = future.get();
            Story story;
            if (snapshot.exists()) {
                story = snapshot.toObject(Story.class);
                return story;
            }
        } catch (Exception ignored) {
            System.out.println(ignored.getMessage());
        }

        return null;
    }

    @Override
    public Story[] getStoryByGenre(String genre) {
        List<Story> stories = new ArrayList<Story>();
        // Get a reference to the Firestore database
        Firestore db = FirestoreClient.getFirestore();

        // Define a reference to the stories collection
        CollectionReference usersRef = db.collection("stories");

        // Retrieve all documents from the stories collection

        ApiFuture<QuerySnapshot> querySnapshot = usersRef.get();

        try {
            for (QueryDocumentSnapshot docSnapshot : querySnapshot.get()) {
                Story s = docSnapshot.toObject(Story.class);
                if (s.getGenre().equalsIgnoreCase(genre)) {
                    stories.add(s);
                }

            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());

        }
        return stories.toArray(new Story[0]);
    }

    @Override
    public Boolean removeStory(String storyID) {
        // Create a new Firestore client
        Firestore firestoreClient = FirestoreClient.getFirestore();

        // Get a reference to the document you want to update
        DocumentReference docRef = firestoreClient.collection("stories").document(storyID);

        ApiFuture<WriteResult> collection = docRef.delete();
        return true;
    }


    @Override
    public List<Story> getStories() {
        List<Story> stories = new ArrayList<Story>();
        // Get a reference to the Firestore database
        Firestore db = FirestoreClient.getFirestore();

        // Define a reference to the stories collection
        CollectionReference usersRef = db.collection("stories");

        // Retrieve all documents from the stories collection

        ApiFuture<QuerySnapshot> querySnapshot = usersRef.get();


        try {
            for (QueryDocumentSnapshot docSnapshot : querySnapshot.get()) {
                stories.add(docSnapshot.toObject(Story.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return stories;
    }

    public String getNextId() throws ExecutionException, InterruptedException {

        List<Story> stories = getStories();
        stories.sort((o1, o2) -> {
            if (o1.getStoryID() == null || o2.getStoryID() == null) {
                return 0;
            }
            int dif = Integer.parseInt(o1.getStoryID()) - Integer.parseInt(o2.getStoryID());
            if (dif < 0) {
                return 1;
            }else if (dif > 0){
                return -1;
            }
            return 0;
        });
        int nex = Integer.parseInt(stories.get(0).getStoryID()) + 1;
        return nex + "";

    }

    public boolean exists(Story story){
        List<Story> stories = getStories();
        return stories.contains(story);
    }
}