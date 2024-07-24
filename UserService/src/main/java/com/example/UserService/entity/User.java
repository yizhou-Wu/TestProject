package com.example.UserService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @JsonIgnore
    private Set<Role> roleList;
}
