package com.example.UserService.controller;

import com.example.UserService.domain.UserRequestBody;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.UserService.entity.User;
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
                first_name(requestBody.getFirstName()).
                last_name(requestBody.getLastName()).
                email(requestBody.getEmailAdd()).build();
        System.out.println(user.toString());
        userService.create(user);
        return "1111";
    }
    @GetMapping("/user/getById")
    public User getById(@RequestParam int Id){
       User user = userService.getById(Id);
       if (user == null){
           return null;
       }
       else return user;
    }

    @GetMapping("/user/getByEmail")
    public User getById(@RequestParam String email){
        User user = userService.getByEmail(email);
        if (user == null){
            return null;
        }
        else return user;
    }
}
