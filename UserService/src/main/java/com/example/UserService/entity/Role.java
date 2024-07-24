package com.example.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name")
    private String role_name;
    @Column(name = "create_date")
    private Timestamp create_date;
    @Column(name = "description")
    private String description;
}
