package com.example.restapi.user;

import com.example.restapi.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public String addUser(@RequestBody String newUser) {
        userService.RegistrerUser(newUser);
        return newUser;
    }

    @GetMapping("/api/users")
    public ArrayList<User> test() {
        return Data.connectedUsers;
    }

    @GetMapping("/api/user/{userID}")
    public User findUser(@PathVariable Integer userID) {
        return userService.findUserByID(userID);
    }

    @GetMapping("/api/username/{username}")
    public Integer findUserID(@PathVariable String username) {
        return userService.findUserID(username);
    }
}
