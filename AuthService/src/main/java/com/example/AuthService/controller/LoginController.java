package com.example.AuthService.controller;

import com.example.AuthService.dto.UserDTO;
import com.example.AuthService.security.AuthUserDetail;
import com.example.AuthService.security.JwtUtil;
import com.example.AuthService.service.LoginService;
import com.example.AuthService.domain.LoginRequestBody;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtUtil jwtUtil;

    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService){this.loginService = loginService;}

    @GetMapping("/login")
    public ResponseEntity<String> LoginProcess(@RequestBody LoginRequestBody loginRequestBody){
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestBody.getUsername(), loginRequestBody.getPassword())
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails authUserDetail = (AuthUserDetail) authentication.getPrincipal();
        String token = jwtUtil.generateToken(authUserDetail);
        System.out.println(authUserDetail.getAuthorities());
        System.out.println(token);
        return ResponseEntity.ok("Login Success!");
    }
}
