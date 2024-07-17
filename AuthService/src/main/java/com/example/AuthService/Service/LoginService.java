package com.example.AuthService.Service;

import com.example.AuthService.dao.LoginDAO;
import com.example.AuthService.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoginService {
    private LoginDAO loginDAO;
    @Autowired
    public LoginService(LoginDAO loginDAO){this.loginDAO = loginDAO;}
    @Transactional
    public void create(Login login){
        loginDAO.create(login);
    }
}
