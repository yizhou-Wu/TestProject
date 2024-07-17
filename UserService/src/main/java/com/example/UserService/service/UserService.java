package com.example.UserService.service;

import com.example.UserService.dao.UserDAO;
import com.example.UserService.entity.User;
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
    @Transactional
    public User getById(int Id){
        return userDAO.getById(Id);
    }
    @Transactional
    public User getByEmail(String email){
        return userDAO.getByEmail(email);
    }

}
