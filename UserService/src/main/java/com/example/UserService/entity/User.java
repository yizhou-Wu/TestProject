package com.example.UserService.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "password")
    private String password;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
}
