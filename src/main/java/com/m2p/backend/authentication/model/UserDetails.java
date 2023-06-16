package com.m2p.backend.authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_credentials")
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails details = (UserDetails) o;
        return Objects.equals(id, details.id) && Objects.equals(username, details.username) && Objects.equals(password,details.password) && Objects.equals(email,details.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,username,email,password);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email +'\''+
                ", password=" + password +'\''+
                '}';
    }
}
