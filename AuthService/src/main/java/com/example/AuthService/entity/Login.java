package com.example.AuthService.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
@Table(name = "login")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "attemp_time")
    private Timestamp attemp_time;
    @Column(name = "is_success")
    private int is_success;
}
