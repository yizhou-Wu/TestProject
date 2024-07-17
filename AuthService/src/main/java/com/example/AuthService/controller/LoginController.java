package com.example.AuthService.controller;

import com.example.AuthService.Service.LoginService;
import com.example.AuthService.domain.LoginRequestBody;
import com.example.AuthService.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@RestController
public class LoginController {
    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService){this.loginService = loginService;}

    @GetMapping("/login")
    public void LoginProcess(@RequestBody LoginRequestBody loginRequestBody){
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        Login login = Login.builder()
                .user_id(1)
                .attemp_time(currentTimestamp)
                .build();
        loginService.create(login);
    }
}
