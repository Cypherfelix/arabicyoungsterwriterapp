package com.example.arabicyoungsterwriterapp.service;

import com.example.arabicyoungsterwriterapp.model.Story;
import com.example.arabicyoungsterwriterapp.repository.StoryRepositoryImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
public class StoryService {
    private StoryRepositoryImpl repository;
    public StoryService(StoryRepositoryImpl storyRepository) {
        this.repository = storyRepository;
    }

    public List<Story> seed() {
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            File file = new File(getClass().getClassLoader().getResource("my_data.json").getFile());
            List<Story> objectList = objectMapper.
                    readValue(new File("my_data.json"), new TypeReference<List<Story>>() {});
            for (Story story : objectList) {
//                repository.addStory(story);
//                System.out.println("Added");
            }
            return null;
        } catch (IOException e) {
            return null;
        }

    }

    public void seedUpdate(){
        List<String> genres = Arrays.asList("Fantasy", "Sci-Fi", "Mystery", "Romance", "Thriller", "Horror", "Comedy", "Drama");
        List<Story> stories = new ArrayList<>();
        stories = repository.getStories();
        Random r = new Random();
        for (Story story : stories) {
            if (story.getStoryID() != null) {
                int i = r.nextInt(genres.size());
                story.setGenre(genres.get(i));
                repository.updateStory(story);
                System.out.println("done");
            }


        }
    }

    public void seedGetLast(){
        try {
            System.out.println(repository.getNextId());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
