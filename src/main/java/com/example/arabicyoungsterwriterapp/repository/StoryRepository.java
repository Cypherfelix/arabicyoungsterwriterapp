package com.example.arabicyoungsterwriterapp.repository;

import com.example.arabicyoungsterwriterapp.FirebaseConfig;
import com.example.arabicyoungsterwriterapp.model.Story;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository {

    // Get a reference to the Firebase Realtime Database
//    FirebaseDatabase database = new FirebaseConfig().firebaseDatabase();

    // Use the database instance to perform CRUD operations on the Story class
    Boolean addStory(Story s);

    Boolean updateStory(Story s);

    Story getStory(String storyID);

    Story[] getStoryByGenre(String genre);

    Boolean removeStory(String storyID);

    List<Story> getStories();

}