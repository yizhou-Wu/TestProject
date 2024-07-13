package com.example.userService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRequestBody {
    String firstName;
    String lastName;
    String emailAdd;

}
