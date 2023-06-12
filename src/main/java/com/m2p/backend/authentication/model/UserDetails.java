package com.m2p.backend.authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "userCredentials")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 20,unique = true)
    private String username;

    @Column(name = "email",nullable = false, length = 30,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
