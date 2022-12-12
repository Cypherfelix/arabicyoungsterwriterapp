package com.example.arabicyoungsterwriterapp.service;

import com.example.arabicyoungsterwriterapp.model.User;
import com.example.arabicyoungsterwriterapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        System.out.println("Hello");
        return userRepository.addUser(user);
    }

    public User login(User user) {
        return userRepository.getUser(user.getEmail(), user.getPassword());
    }

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return userRepository.getUsers();
    }
}
