package com.example.arabicyoungsterwriterapp.controller;

import com.example.arabicyoungsterwriterapp.model.Story;
import com.example.arabicyoungsterwriterapp.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    // Inject the StoryRepository instance
    @Autowired
    private StoryRepository storyRepository;

    // Define the REST endpoints for the CRUD operations on the Story class

    // Add a new story to the repository
    @PostMapping("/story")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean addStory(@RequestBody Story s) {
        return storyRepository.addStory(s);
    }

    // Update an existing story in the repository
    @PutMapping("/story/{storyID}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean updateStory(@PathVariable String storyID, @RequestBody Story s) {
        s.setStoryID(storyID);
        return storyRepository.updateStory(s);
    }

    // Retrieve a story by ID from the repository
    @GetMapping("/story/{storyID}")
    @ResponseStatus(HttpStatus.OK)
    public Story getStory(@PathVariable String storyID) {
        return storyRepository.getStory(storyID);
    }

    // Retrieve stories by genre from the repository
    @GetMapping("/story/genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Story> getStoryByGenre(@PathVariable String genre) {
        return List.of(storyRepository.getStoryByGenre(genre));
    }

    // Retrieve stories by genre from the repository
    @GetMapping("/story")
    @ResponseStatus(HttpStatus.OK)
    public List<Story> getStories() {
        return storyRepository.getStories();
    }

    // Remove a story from the repository
    @DeleteMapping("/story/{storyID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStory(@PathVariable String storyID) {
        storyRepository.removeStory(storyID);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test Endpoint OK");
    }


}
