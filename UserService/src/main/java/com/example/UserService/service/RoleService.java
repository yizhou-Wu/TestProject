package com.example.UserService.service;

import com.example.UserService.dao.RoleDAO;
import com.example.UserService.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService {
    private RoleDAO roleDAO;
    @Autowired
    public RoleService(RoleDAO roleDAO){this.roleDAO = roleDAO;}
    @Transactional
    public int create(String role_name, String description){
        return roleDAO.create(role_name, description);
    }
    @Transactional
    public Role getById(int id){
        return roleDAO.getById(id);
    }
}
