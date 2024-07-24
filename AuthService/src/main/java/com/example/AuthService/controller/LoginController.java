package com.example.AuthService.controller;

import com.example.AuthService.service.LoginService;
import com.example.AuthService.domain.LoginRequestBody;
import com.example.AuthService.entity.Login;
import com.example.AuthService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
public class LoginController {
    @Autowired
    private RestTemplate restTemplate;
    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService){this.loginService = loginService;}

    @GetMapping("/login")
    public void LoginProcess(@RequestBody LoginRequestBody loginRequestBody){
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        String getByEmail = "http://user-service/user-service/user/getByEmail?email="+loginRequestBody.getUsername();
        System.out.println(getByEmail);
        HttpHeaders headers = new HttpHeaders();
//        headers.set("email",loginRequestBody.getUsername());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<User> responseEntity = restTemplate.exchange(getByEmail, HttpMethod.GET,entity,User.class);
        User currentUser = responseEntity.getBody();
        if (currentUser == null) {
            System.out.println("NULL USER");
            return;
        }
        else{
            System.out.println(currentUser.getId() + ","+currentUser.getEmail());
            Login login = Login.builder()
                    .user_id(currentUser.getId())
                    .attemp_time(currentTimestamp)
                    .build();
            loginService.create(login);
        }

    }
}
