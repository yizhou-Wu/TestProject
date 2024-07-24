package com.example.UserService.controller;

import com.example.UserService.domain.UserRequestBody;
import com.example.UserService.entity.Role;
import com.example.UserService.service.RoleService;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.UserService.entity.User;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public UserController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
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
    public User getByEmail(@RequestParam String email){
        System.out.println(email);
        User user = userService.getByEmail(email);
        if (user == null){
            return null;
        }
        else return user;
    }
    @PostMapping("/user/setRole")
    public void setRole(@RequestParam int user_id,@RequestParam int role_id){
        Role role = roleService.getById(role_id);
        if (role == null) return;
        userService.setRole(user_id,role);
    }
    @GetMapping("/user/getRoleById")
    public List<Role> getRoleById (@RequestParam int id){
        List<Role> list = userService.getRolesById(id);
        if (list == null){
            System.out.println("NO SUCH USER");
        }
        return list;
    }
}
