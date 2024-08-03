package com.example.AuthService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String password;
    private String username;
    private String email;
    private int is_active;

    private Set<Role> roleSet;

}
