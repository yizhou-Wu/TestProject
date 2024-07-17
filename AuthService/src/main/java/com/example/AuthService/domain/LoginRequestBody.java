package com.example.AuthService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequestBody {
    String Username;
    String Password;
}
