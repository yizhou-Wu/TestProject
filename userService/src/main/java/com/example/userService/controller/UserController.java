package com.example.userService.controller;

import com.example.userService.domain.UserRequestBody;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.userService.entity.User;
@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/user/create")
    public String create(@RequestBody UserRequestBody requestBody){
        User user = User.builder().
                firstName(requestBody.getFirstName()).
                lastName(requestBody.getLastName()).
                emailAdd(requestBody.getEmailAdd()).build();
        System.out.println(user.toString());
        userService.create(user);
        return "1111";
    }

}
