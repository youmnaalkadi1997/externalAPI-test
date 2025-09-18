package org.example.externalapitest.controller;


import org.example.externalapitest.model.ReqNewUser;
import org.example.externalapitest.model.ResNewUser;
import org.example.externalapitest.model.User;
import org.example.externalapitest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResNewUser addUser(@RequestBody ReqNewUser reqNewUser)  {
        return  userService.addUser(reqNewUser);
    }
}
