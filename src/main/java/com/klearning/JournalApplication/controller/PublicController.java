package com.klearning.JournalApplication.controller;

import com.klearning.JournalApplication.entity.User;
import com.klearning.JournalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user) {
        userService.createUser(user);
        return true;
    }
}
