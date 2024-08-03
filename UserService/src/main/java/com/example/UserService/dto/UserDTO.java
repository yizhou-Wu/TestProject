package com.example.UserService.dto;

import com.example.UserService.entity.Role;
import com.example.UserService.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDTO  {
    User user;
    Set<Role> roleSet;
}
