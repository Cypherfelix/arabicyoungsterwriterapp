package com.example.arabicyoungsterwriterapp;

import com.example.arabicyoungsterwriterapp.controller.UserController;
import com.example.arabicyoungsterwriterapp.model.User;
import com.example.arabicyoungsterwriterapp.service.UserService;
import com.example.arabicyoungsterwriterapp.utill.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testRegisterUser() throws Exception {
        // Create a test User object
        User user = new User("user1", "password", "User One", "user1");

        // Mock the userService.registerUser() method to return true
//        when(userService.registerUser(user)).thenReturn(true);

        System.out.println(userService.registerUser(user));

//        // Perform a POST request to the /register endpoint
//        mockMvc.perform(post("/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JsonUtil.toJson(user)))
//                // Verify that the response has a HTTP status of OK (200)
//                .andExpect(status().isOk())
//                // Verify that the response body is equal to "true"
//                .andExpect(content().string("true"));
    }

    @Test
    public void testLogin() throws Exception {
        // Create a test User object
        User user = new User("user1", "password", "User One", "user1");

        // Mock the userService.login() method to return the test User object
        when(userService.login(user)).thenReturn(user);

        // Perform a POST request to the /login endpoint
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user)))
                // Verify that the response has a HTTP status of OK (200)
                .andExpect(status().isOk())
                // Verify that the response body is equal to the JSON representation of the test User object
                .andExpect(content().json(JsonUtil.toJson(user)));
    }

    // Write similar test methods for the other controller methods

}
