package com.example.userService.service;

import com.example.userService.dao.UserDAO;
import com.example.userService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserService {
    private UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Transactional
    public void create(User user){
        System.out.println("Service!");
        userDAO.create(user);
    }

}
