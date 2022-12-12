package com.example.arabicyoungsterwriterapp;

import com.example.arabicyoungsterwriterapp.model.User;
import com.example.arabicyoungsterwriterapp.repository.StoryRepository;
import com.example.arabicyoungsterwriterapp.repository.StoryRepositoryImpl;
import com.example.arabicyoungsterwriterapp.repository.UserRepository;
import com.example.arabicyoungsterwriterapp.service.StoryService;
import com.example.arabicyoungsterwriterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ArabicyoungsterwriterappApplication {

    @Autowired
    private static ResourcePatternResolver resolver;

    public static List<Resource> listAllResources() throws IOException {
        resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:**/*.json");
        List<Resource> resourcePaths = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getURL().toString().contains("arabicstoryclient.json")) {
                resourcePaths.add(resource);
            }
        }
        return resourcePaths;
    }

    public static void main(String[] args) throws IOException {
        FirebaseConfig f = new FirebaseConfig();
        f.firebaseDatabase();
        SpringApplication.run(ArabicyoungsterwriterappApplication.class, args);

        User user = new User("user1", "password", "User One", "user1");

        // Mock the userService.registerUser() method to return true
//        when(userService.registerUser(user)).thenReturn(true);

        UserService userService = new UserService(new UserRepository());
        StoryService storyService = new StoryService(new StoryRepositoryImpl());
        storyService.seedGetLast();
//        System.out.println(userService.login(user));
        try {
            System.out.println(userService.getUsers());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
