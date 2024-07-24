package com.example.UserService.controller;

import com.example.UserService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService){this.roleService = roleService;}
    @PostMapping("/create")
    public void create(@RequestParam String role_name, @RequestParam String description){
        int id = roleService.create(role_name,description);
        if (id == -1){
            System.out.println("FAILED");
        }
    }
}
