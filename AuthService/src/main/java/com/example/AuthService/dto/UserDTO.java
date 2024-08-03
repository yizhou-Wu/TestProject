package com.example.AuthService.dto;

import com.example.AuthService.entity.Role;
import com.example.AuthService.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDTO {
    User user;
    Set<Role> roleSet;

}
