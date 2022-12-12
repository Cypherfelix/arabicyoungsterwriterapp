package com.example.arabicyoungsterwriterapp.controller;

import com.example.arabicyoungsterwriterapp.model.User;
import com.example.arabicyoungsterwriterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }
}
