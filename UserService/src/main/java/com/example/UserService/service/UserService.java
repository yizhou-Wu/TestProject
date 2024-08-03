package com.example.UserService.service;

import com.example.UserService.dao.UserDAO;
import com.example.UserService.entity.Role;
import com.example.UserService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Transactional
    public void create(User user){
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
    @Transactional
    public User getByUsername(String username){
        return userDAO.getByUsername(username);
    }
    @Transactional
    public Set<Role> getRolesById(int id){
        return userDAO.getRolesById(id);
    }
    @Transactional
    public void setRole(int user_id, Role role){
        userDAO.setRole(user_id,role);
    }

}
